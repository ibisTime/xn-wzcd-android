package com.cdkj.wzcd.main.credit.module.zrzl;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.adapters.ViewPagerAdapter;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActZrzlBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.ZrzlStepAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlStepBean;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepCltFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepClxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkcltFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkrxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepFyxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepJbxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepJjlxrFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepSmdctFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : qianLei
 * @since : 2019/12/26 14:37
 */
public class ZrzlActivity extends AbsBaseLoadActivity {

    public static String UPLOAD = "upload";
    public static String SET_UPLOAD_RESULT = "set_upload_result";

    private ActZrzlBinding mBinding;

    public String code;
    public ZrzlBean data;
    public static String slRegion;//业务发生地
    public static String slBizType;//二手车 /新车

    private List<ZrzlStepBean> steps = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private boolean isShowCrate;

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZrzlActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }


    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_zrzl, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        initStepDatas();
        initStep();

        init();
        initListener();

    }


    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
        isShowCrate = getIntent().getBooleanExtra(CdRouteHelper.DATA_SIGN2, false);
        getDetail(true);
    }

    private void initListener() {
        mBinding.flBack.setOnClickListener(view -> {
            finish();
        });

        mBinding.llStepSelect.setOnClickListener(view -> {
            if (TextUtils.isEmpty(code)) {
                UITipDialog.showFail(this, "请先填写并保存《基本信息页》");
                return;
            }

            mBinding.llStep.setVisibility(View.VISIBLE);
        });

        mBinding.llStep.setOnClickListener(view -> {
            mBinding.llStep.setVisibility(View.GONE);
        });
    }

    private void initStepDatas() {
        steps.add(new ZrzlStepBean(true, "zrzl_zbxi", "基本信息"));
        steps.add(new ZrzlStepBean(false, "zrzl_zdrxx", "贷款人信息"));
        steps.add(new ZrzlStepBean(false, "zrzl_jjlxr", "紧急联系人"));
        steps.add(new ZrzlStepBean(false, "zrzl_dkxx", "贷款信息"));
//        steps.add(new ZrzlStepBean(false, "zrzl_fyxx", "费用信息"));
        steps.add(new ZrzlStepBean(false, "zrzl_clxx", "车辆信息"));
        steps.add(new ZrzlStepBean(false, "zrzl_dkclt", "贷款材料图"));
        steps.add(new ZrzlStepBean(false, "zrzl_smdct", "上门调查图"));
        steps.add(new ZrzlStepBean(false, "zrzl_clt", "车辆图"));
    }

    private void initStep() {

        ZrzlStepAdapter mAdapter = new ZrzlStepAdapter(steps);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            for (ZrzlStepBean bean : mAdapter.getData()) {
                bean.setSelected(false);
            }

            ZrzlStepBean item = mAdapter.getData().get(position);
            item.setSelected(true);
            mBinding.tvTitle.setText(item.getName());

            mAdapter.notifyDataSetChanged();

            setShowIndex(position);
            mBinding.llStep.setVisibility(View.GONE);
        });

        mBinding.rvStep.setAdapter(mAdapter);
        mBinding.rvStep.setLayoutManager(new GridLayoutManager(this, 4));
    }

    private void initFragments() {
        fragments.add(StepJbxxFragment.getInstance(false));
        fragments.add(StepDkrxxFragment.getInstance(false));
        fragments.add(StepJjlxrFragment.getInstance(false));//3
        fragments.add(StepDkxxFragment.getInstance(false));
//        fragments.add(StepFyxxFragment.getInstance(false));
        fragments.add(StepClxxFragment.getInstance(false));//6
        fragments.add(StepDkcltFragment.getInstance(false));
        fragments.add(StepSmdctFragment.getInstance(false));
        fragments.add(StepCltFragment.getInstance(false));//9
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mBinding.vpStep.setPagingEnabled(false);//禁止左右切换
        mBinding.vpStep.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.vpStep.setOffscreenPageLimit(fragments.size());
        mBinding.vpStep.setCurrentItem(0);
    }

    /**
     * 设置要显示的界面
     *
     * @param index
     */
    private void setShowIndex(int index) {

        mBinding.vpStep.setCurrentItem(index, false);
    }

    @Subscribe
    public void setUploadResult(EventBean bean) {
        if (bean.getTag().equals(SET_UPLOAD_RESULT)) {

            int position = Integer.parseInt(bean.getValue1());
            if (position == 0) {

                code = bean.getValue2();
                getDetail(false);
            }

            steps.get(position).setResult(true);
        }

        if (bean.getTag().equals(UPLOAD)) {

//            if (!steps.get(0).isResult()) {
//                UITipDialog.showFail(this, "请完成《" + steps.get(0).getName() + "》页的资料填写，并保存");
//            }
//            if (!steps.get(1).isResult()) {
//                UITipDialog.showFail(this, "请完成《" + steps.get(1).getName() + "》页的资料填写，并保存");
//            }
//            if (!steps.get(2).isResult()) {
//                UITipDialog.showFail(this, "请完成《" + steps.get(2).getName() + "》页的资料填写，并保存");
//            }
//            if (!steps.get(4).isResult()) {
//                UITipDialog.showFail(this, "请完成《" + steps.get(4).getName() + "》页的资料填写，并保存");
//            }
//            if (!steps.get(5).isResult()) {
//                UITipDialog.showFail(this, "请完成《" + steps.get(5).getName() + "》页的资料填写，并保存");
//            }

            uploadConfirm();

        }

        if (bean.getTag().equals("showLoading")) {
            showLoadingDialog();
        }

        if (bean.getTag().equals("disMissLoading")) {
            disMissLoading();
        }

    }


    private void uploadConfirm() {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632539", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(ZrzlActivity.this, "提交成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void getDetail(boolean isSetView) {
        if (TextUtils.isEmpty(code)) {

            initFragments();
            initViewPager();

            return;
        }

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getZrzlDetail("632516", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ZrzlBean>(this) {
            @Override
            protected void onSuccess(ZrzlBean d, String SucMessage) {
                data = d;

                if (null != data.getCreditUserList()) {
                    for (DkrxxBean bean : data.getCreditUserList()) {

                        if (bean.getLoanRole().equals("1")) {

                            bean.setIdFront(getAttachUrl("id_no_front_apply"));
                            bean.setIdReverse(getAttachUrl("id_no_reverse_apply"));
                            bean.setHoldIdCardPdf(getAttachUrl("hold_id_card_apply"));

                        } else if (bean.getLoanRole().equals("2")) {

                            bean.setIdFront(getAttachUrl("id_no_front_gh"));
                            bean.setIdReverse(getAttachUrl("id_no_reverse_gh"));
                            bean.setHoldIdCardPdf(getAttachUrl("hold_id_card_gh"));

                        } else if (bean.getLoanRole().equals("3")) {

                            bean.setIdFront(getAttachUrl("id_no_front_gua"));
                            bean.setIdReverse(getAttachUrl("id_no_reverse_gua"));
                            bean.setHoldIdCardPdf(getAttachUrl("hold_id_card_gua"));

                        } else if (bean.getLoanRole().equals("4")) {

                            bean.setIdFront(getAttachUrl("id_no_front_gua1"));
                            bean.setIdReverse(getAttachUrl("id_no_reverse_gua1"));
                            bean.setHoldIdCardPdf(getAttachUrl("hold_id_card_gua1"));

                        } else if (bean.getLoanRole().equals("5")) {

                            bean.setIdFront(getAttachUrl("id_no_front_gh1"));
                            bean.setIdReverse(getAttachUrl("id_no_reverse_gh1"));
                            bean.setHoldIdCardPdf(getAttachUrl("hold_id_card_gh1"));

                        }

                    }

                }

                if (isSetView) {
                    initFragments();
                    initViewPager();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private String getAttachUrl(String kName) {

        if (null == data.getAttachments()) {
            return null;
        }

        for (ZrzlBean.AttachmentsBean bean : data.getAttachments()) {

            if (bean.getKname().equals(kName)) {
                return bean.getUrl();
            }

        }

        return null;
    }


}
