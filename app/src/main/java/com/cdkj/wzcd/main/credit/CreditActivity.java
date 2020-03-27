package com.cdkj.wzcd.main.credit;

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
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
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
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailCzrzFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailDzxqFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailFkxqFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailHkjhFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailLjxqFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.detail.DetailRdxqFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepCltFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepClxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkcltFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkrxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepDkxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepFyxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepJbxxFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepJjlxrFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.step.StepSmdctFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : qianLei
 * @since : 2020/1/1 23:13
 */
public class CreditActivity extends AbsBaseLoadActivity {

    private ActZrzlBinding mBinding;

    public String code;
    public ZrzlBean data;

    private List<ZrzlStepBean> steps = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditActivity.class);
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

        init();
        initListener();

        initStepDatas();
        initStep();


    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
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
        steps.add(new ZrzlStepBean(true, "credit_zbxi", "基本信息"));
        steps.add(new ZrzlStepBean(false, "credit_zdrxx", "贷款人信息"));
        steps.add(new ZrzlStepBean(false, "credit_jjlxr", "紧急联系人"));
        steps.add(new ZrzlStepBean(false, "credit_dkxx", "贷款信息"));
//        steps.add(new ZrzlStepBean(false, "credit_fyxx", "费用信息"));
        steps.add(new ZrzlStepBean(false, "credit_clxx", "车辆信息"));
        steps.add(new ZrzlStepBean(false, "credit_dkclt", "贷款材料图"));
        steps.add(new ZrzlStepBean(false, "credit_smdct", "上门调查图"));
        steps.add(new ZrzlStepBean(false, "credit_clt", "车辆图"));

        steps.add(new ZrzlStepBean(false, "credit_dzxq", "垫资详情"));
        steps.add(new ZrzlStepBean(false, "credit_ljxq", "理件详情"));
        steps.add(new ZrzlStepBean(false, "credit_fkxq", "放款详情"));
        steps.add(new ZrzlStepBean(false, "credit_rdxq", "入档详情"));
        steps.add(new ZrzlStepBean(false, "credit_czrz", "操作日志"));
        steps.add(new ZrzlStepBean(false, "credit_hkjh", "还款计划"));
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

    /**
     * 设置要显示的界面
     *
     * @param index
     */
    private void setShowIndex(int index) {

        mBinding.vpStep.setCurrentItem(index, false);
    }

    private void getDetail(boolean isSetView) {

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", code);
//        map.put("teamCode", SPUtilHelper.getTeamCode());

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

                initFragments();
                initViewPager();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initFragments() {
        fragments.add(StepJbxxFragment.getInstance(true));
        fragments.add(StepDkrxxFragment.getInstance(true));
        fragments.add(StepJjlxrFragment.getInstance(true));
        fragments.add(StepDkxxFragment.getInstance(true));
//        fragments.add(StepFyxxFragment.getInstance(true));
        fragments.add(StepClxxFragment.getInstance(true));
        fragments.add(StepDkcltFragment.getInstance(true));
        fragments.add(StepSmdctFragment.getInstance(true));
        fragments.add(StepCltFragment.getInstance(true));

        fragments.add(DetailDzxqFragment.getInstance());
        fragments.add(DetailLjxqFragment.getInstance());
        fragments.add(DetailFkxqFragment.getInstance());
        fragments.add(DetailRdxqFragment.getInstance());
        fragments.add(DetailCzrzFragment.getInstance());
        fragments.add(DetailHkjhFragment.getInstance());
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
