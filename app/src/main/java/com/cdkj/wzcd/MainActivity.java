package com.cdkj.wzcd;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.CommonDialog;
import com.cdkj.baselibrary.dialog.InputDialog;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityMainBinding;
import com.cdkj.wzcd.model.CollectionBean;
import com.cdkj.wzcd.model.NodeModel;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.module.datatransfer.TransferActivity;
import com.cdkj.wzcd.module.tool.calculator.CalculautorActivity;
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
import com.cdkj.wzcd.module.work.join_approval.JoinApplyListActivity;
import com.cdkj.wzcd.util.NodeHelper;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.util.UserHelper.NQZY;
import static com.cdkj.wzcd.util.UserHelper.YWY;
import static com.cdkj.wzcd.util.UserHelper.ZHRY;

public class MainActivity extends AbsBaseLoadActivity {

    private static final String TAG = "ppppppMainActivity";
    // 节点列表
    public static List<NodeModel> BASE_NODE_LIST = new ArrayList<>();

    private UserModel mUserModel;

    private ActivityMainBinding mBinding;

    private final int REQUEST_CODE = 200;


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

                getUserInfoRequest();

            }

            @Override
            public void onReqFailure(String errorCode, String errorMessage) {

            }
        });
    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest() {

        if (!SPUtilHelper.isLoginNoStart()) {  //没有登录不用请求
            return;
        }

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserInfoDetails("630067", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

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
                disMissLoading();
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

        if (TextUtils.equals(data.getRoleCode(), ZHRY)) { // 驻行人员
            mBinding.tvRole.setText("[驻行人员]");

            mBinding.mySrAdvanceFund.setVisibility(View.GONE);

            mBinding.mySrLoan.setVisibility(View.VISIBLE);
            mBinding.mySrCldy.setVisibility(View.VISIBLE);

            mBinding.llTool1.setVisibility(View.GONE);
            mBinding.llTool2.setVisibility(View.GONE);

            mBinding.mySrLskh.setVisibility(View.GONE);
            mBinding.mySrJcdy.setVisibility(View.VISIBLE);

            mBinding.llZlcd.setVisibility(View.GONE);

        } else if (TextUtils.equals(data.getRoleCode(), YWY)) {// 业务员
            mBinding.tvRole.setText("[业务员]");

        } else if (TextUtils.equals(data.getRoleCode(), NQZY)) {// 内勤专员
            mBinding.tvRole.setText("[内勤专员]");

        } else {
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

        //准入申请
        mBinding.mySrZrsq.setOnClickListener(v -> {
            JoinApplyListActivity.open(this);
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
        // 月供计算器
        mBinding.mySrCalculautor.setOnClickListener(view -> {
            CalculautorActivity.open(this);
        });

        //资料上传
        mBinding.mySrZlcd.setOnClickListener(view -> {
            TransferActivity.open(this);
        });
        //扫码收件
        mBinding.mySrZxing.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }


    /**
     * 退出登录
     */
    private void logOut() {
        showDoubleWarnListen("你确定要退出当前账号吗?", view -> {
            SPUtilHelper.logOutClear();
            UITipDialog.showSuccess(this, "退出成功", dialogInterface -> {
                finish();

                SignInActivity.open(this, false);
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）

            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.i("pppppp", "onActivityResult: 返回的数据为:" + result);
                    CollectionBean bean;
                    try {
                        bean = new Gson().fromJson(result, CollectionBean.class);
                    } catch (Exception e) {
                        UITipDialog.showInfo(this, "二维码解析失败");
                        return;
                    }

                    if (TextUtils.isEmpty(bean.getType()) || bean.getCodeList() == null || bean.getCodeList().size() == 0) {
                        UITipDialog.showInfo(this, "请扫描完整的二维码");
                        return;
                    }
                    //解析成功去进行  自动收件
                    if ("2".equals(bean.getType())) {
                        //判断是不是gps收件
                        //gps收件右两种情况   1.收件并审核通过   2.补件
                        new CommonDialog(this)
                                .builder()
                                .setTitle("资料收件")
                                .setNegativeBtn("收件待补件", view -> {

                                    shouReissueRequest(bean.getCodeList());

                                })
                                .setPositiveBtn("收件并审核", view -> {
                                    pickUpRequest(bean.getCodeList());
                                })
                                .show();
                    } else {
                        pickUpRequest(bean.getCodeList());
                    }


                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    /**
     * 收件
     * 如果是资料收件  请传true  收件完成后跳转到审核界面
     */
    public void pickUpRequest(List<String> codeList) {
        if (codeList == null || codeList.size() == 0) {
            return;
        }
//        List<String> codeList = new ArrayList<>();
//        codeList.add(code);

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("codeList", codeList);
        hashMap.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("632151", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(MainActivity.this, "收件成功", dialogInterface -> {

                    });
                } else {
                    UITipDialog.showFail(MainActivity.this, "收件失败");
                }
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                Log.i(TAG, "onReqFailure: " + errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


    /**
     * 弹出补件说明框
     */
    private void shouReissueRequest(List<String> codeList) {
        new InputDialog(MainActivity.this)
                .builder()
                .setTitle("请输入补件原因(必填)")
                .setContentHintText("")
                .setNegativeBtn("取消", null)
                .setPositiveBtn("确定", (view, inputMsg) -> {
                    if (TextUtils.isEmpty(inputMsg)) {
                        UITipDialog.showInfo(MainActivity.this, "操作失败请输入补件原因");
                        return;
                    }
                    reissueRequest(codeList, inputMsg);
                })
                .show();
    }

    /**
     * 补件
     */
    private void reissueRequest(List<String> codeList, String mesg) {

        if (codeList == null || codeList.size() == 0) {
            return;
        }

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("codeList", codeList);
//        hashMap.put("code", code);
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("supplementReason", mesg);


        Call call = RetrofitUtils.getBaseAPiService().successRequest("632152", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(MainActivity.this, "操作成功");
                } else {
                    UITipDialog.showFail(MainActivity.this, "操作失败");
                }
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                Log.i(TAG, "onReqFailure: " + errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
