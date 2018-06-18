package com.cdkj.wzcd;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityMainBinding;
import com.cdkj.wzcd.model.NodeModel;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.module.datatransfer.DataTransferActivity;
import com.cdkj.wzcd.module.tool.fabaohe.FbhListActivity;
import com.cdkj.wzcd.module.tool.fabrication.FabricationListActivity;
import com.cdkj.wzcd.module.tool.gps.GpsListActivity;
import com.cdkj.wzcd.module.tool.gps_install.GPSInstallListActivity;
import com.cdkj.wzcd.module.tool.history.HistoryUserActivity;
import com.cdkj.wzcd.module.tool.jcdy.JcdyListActivity;
import com.cdkj.wzcd.module.tool.mismatching.MismatchingListActivity;
import com.cdkj.wzcd.module.tool.uservoid.UserToVoidActivity;
import com.cdkj.wzcd.module.user.SignInActivity;
import com.cdkj.wzcd.module.work.advancefund.AdvanceFundListActivity;
import com.cdkj.wzcd.module.work.bank_loan.BankLoanListActivity;
import com.cdkj.wzcd.module.work.cldy.BssCldyListActivity;
import com.cdkj.wzcd.module.work.credit.CreditListActivity;
import com.cdkj.wzcd.util.BizTypeHelper;
import com.cdkj.wzcd.util.NodeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.util.DataDictionaryHelper.budget_orde_biz_typer;
import static com.cdkj.wzcd.util.UserHelper.NQZY;
import static com.cdkj.wzcd.util.UserHelper.YWY;
import static com.cdkj.wzcd.util.UserHelper.ZHRY;

public class MainActivity extends AbsBaseLoadActivity {

    // 节点列表
    public static List<NodeModel> BASE_NODE_LIST = new ArrayList<>();
    // 购车途径
    public static List<DataDictionary> BASE_BIZ_TYPE = new ArrayList<>();

    private UserModel mUserModel;

    private ActivityMainBinding mBinding;


    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        initListener();

        NodeHelper.getNodeBaseDataRequest(this, "", "", new NodeHelper.NodeInterface() {
            @Override
            public void onSuccess(List<NodeModel> list) {
                BASE_NODE_LIST.clear();
                BASE_NODE_LIST.addAll(list);


                BizTypeHelper.getBizTypeBaseDataRequest(MainActivity.this, budget_orde_biz_typer, new BizTypeHelper.BizTypeInterface() {
                    @Override
                    public void onSuccess(List<DataDictionary> list) {

                        BASE_BIZ_TYPE.clear();
                        BASE_BIZ_TYPE.addAll(list);

                        getUserInfoRequest(true);
                    }

                    @Override
                    public void onReqFailure(String errorCode, String errorMessage) {

                    }
                });

            }

            @Override
            public void onReqFailure(String errorCode, String errorMessage) {

            }
        });
    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest(boolean isShowDialog) {

        if (!SPUtilHelper.isLoginNoStart()) {  //没有登录不用请求
            return;
        }

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserInfoDetails("630067", StringUtils.getJsonToString(map));

        addCall(call);

        if (isShowDialog) showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserModel>(this) {
            @Override
            protected void onSuccess(UserModel data, String SucMessage) {
                mUserModel = data;

                saveUserInfo(data);
                setView(data);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                UITipDialog.showFail(MainActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                if (isShowDialog) disMissLoading();
            }
        });
    }

    /**
     * 保存用户相关信息
     *
     * @param data
     */
    private void saveUserInfo(UserModel data) {
        SPUtilHelper.saveisTradepwdFlag(data.isTradepwdFlag());
        SPUtilHelper.saveUserPhoneNum(data.getMobile());
        SPUtilHelper.saveUserName(data.getRealName());
        SPUtilHelper.saveUserNickName(data.getNickname());
        SPUtilHelper.saveUserPhoto(data.getPhoto());
        SPUtilHelper.saveRoleCode(data.getRoleCode());
        SPUtilHelper.saveUserCompanyCode(data.getRoleCode());
    }

    private void setView(UserModel data) {

        ImgUtils.loadQiNiuBorderLogo(this, data.getPhoto(), mBinding.imAvatar, R.color.white);
        mBinding.tvNick.setText(TextUtils.isEmpty(data.getLoginName()) ? "暂无" : data.getLoginName());
        mBinding.tvCompany.setText(data.getCompanyName());

        if (TextUtils.equals(data.getRoleCode(), ZHRY)){ // 驻行人员
            mBinding.tvRole.setText("[驻行人员]");

            mBinding.mySrAdvanceFund.setVisibility(View.GONE);

            mBinding.mySrLoan.setVisibility(View.VISIBLE);
            mBinding.mySrCldy.setVisibility(View.VISIBLE);

            mBinding.llTool1.setVisibility(View.GONE);
            mBinding.llTool2.setVisibility(View.GONE);

            mBinding.mySrLskh.setVisibility(View.GONE);
            mBinding.mySrJcdy.setVisibility(View.VISIBLE);

            mBinding.llZlcd.setVisibility(View.GONE);

        }else if (TextUtils.equals(data.getRoleCode(), YWY)){// 业务员
            mBinding.tvRole.setText("[业务员]");

        }else if (TextUtils.equals(data.getRoleCode(), NQZY)){// 内勤专员
            mBinding.tvRole.setText("[内勤专员]");

        }else {
            mBinding.tvRole.setText("[其他]");
        }

    }

    private void initListener() {
        mBinding.flRight.setOnClickListener(view -> {

            logOut();
        });

        //
        mBinding.mySrZxdc.setOnClickListener(view -> {
            CreditListActivity.open(this);
        });

        // 财务垫资
        mBinding.mySrAdvanceFund.setOnClickListener(view -> {
            AdvanceFundListActivity.open(this);
        });

        // 银行放款
        mBinding.mySrLoan.setOnClickListener(view -> {
            BankLoanListActivity.open(this);
        });

        //车辆抵押
        mBinding.mySrCldy.setOnClickListener(view -> {
            BssCldyListActivity.open(this);
        });

        // 制卡
        mBinding.mySrFabrication.setOnClickListener(view -> {
            FabricationListActivity.open(this);
        });

        // 发保合
        mBinding.mySrFbh.setOnClickListener(view -> {
            FbhListActivity.open(this);
        });

        // 发保合
        mBinding.mySrMismatching.setOnClickListener(view -> {
            MismatchingListActivity.open(this);
        });


        //gps 安装
        mBinding.mySrGpsaz.setOnClickListener(view -> {
            GPSInstallListActivity.open(this);
        });

        //客户作废
        mBinding.mySrKhzf.setOnClickListener(view -> {
            UserToVoidActivity.open(this);
        });

        //GPS申领
        mBinding.mySrGpssl.setOnClickListener(view -> {
            GpsListActivity.open(this);
        });

        //历史客户
        mBinding.mySrLskh.setOnClickListener(view -> {
            HistoryUserActivity.open(this);
        });

        // 解除抵押
        mBinding.mySrJcdy.setOnClickListener(view -> {
            JcdyListActivity.open(this);
        });

        //资料上传
        mBinding.mySrZlcd.setOnClickListener(view -> {
            DataTransferActivity.open(this);
        });
    }


    /**
     * 退出登录
     */
    private void logOut() {
        showDoubleWarnListen("你确定要退出当前账号吗?", view -> {
            SPUtilHelper.logOutClear();
            UITipDialog.showSuccess(this, "退出成功",dialogInterface -> {
                finish();

                SignInActivity.open(this,false);
            });
        });
    }

}
