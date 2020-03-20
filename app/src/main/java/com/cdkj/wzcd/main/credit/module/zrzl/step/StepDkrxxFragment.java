package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FrgStepZdrxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.DkrxxAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.main.credit.module.zrzl.step.dkrxx.StepDkrxxActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import retrofit2.Call;

import java.util.*;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_loan_role;

/**
 * 主贷人信息
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepDkrxxFragment extends BaseLazyFragment {

    private FrgStepZdrxxBinding mBinding;

    private boolean isDetail;

    private List<DkrxxBean> list = new ArrayList<>();
    private DkrxxAdapter mAdapter;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepDkrxxFragment getInstance(boolean isDetail) {
        StepDkrxxFragment fragment = new StepDkrxxFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CdRouteHelper.DATA_SIGN, isDetail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_zdrxx, null, false);

        initListener();

        init();
        initData();
        initAdapter();

        setList();

        return mBinding.getRoot();
    }


    private void init() {

        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);

    }

    private void initData() {

        List<DataDictionary> listRole = DataDictionaryHelper
                .getListByParentKey(credit_user_loan_role);

        for (DataDictionary dictionary : listRole) {

            String role = dictionary.getDvalue();
            if (dictionary.getDkey().equals("2")) {
                role = role + "1";
            }
            if (dictionary.getDkey().equals("3")) {
                role = role + "1";
            }
            if (dictionary.getDkey().equals("4")) {
                role = role + "2";
            }
            if (dictionary.getDkey().equals("5")) {
                role = role + "2";
            }

            list.add(new DkrxxBean(dictionary.getDkey(), role));

        }

    }


    private void initAdapter() {

        mAdapter = new DkrxxAdapter(list);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            StepDkrxxActivity.open(mActivity, mAdapter.getItem(position), position, isDetail);
        });
        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setLayoutManager(
                new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

    }

    private void initListener() {

        mBinding.btnConfirm.setOnClickListener(view -> {

            if (TextUtils.isEmpty(list.get(0).getBankCreditResult())) {
                ToastUtil.show(mActivity, "请完善" + list.get(0).getLoanRoleName() + "信息");
                return;
            }

            doRequest();
        });

    }

    private ZrzlBean getData() {
        if (isDetail) {
            if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                    || null == ((CreditActivity) mActivity).data) {
                return null;
            }
            return ((CreditActivity) mActivity).data;
        } else {
            if (TextUtils.isEmpty(((ZrzlActivity) mActivity).code)
                    || null == ((ZrzlActivity) mActivity).data) {
                return null;
            }
            return ((ZrzlActivity) mActivity).data;
        }

    }

    private void setList() {

        ZrzlBean data = getData();
        if (null == data) {
            return;
        }

        if (null == data.getCreditUserList()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {

            for (DkrxxBean dkrxxBean : data.getCreditUserList()) {

                if (list.get(i).getLoanRole().equals(dkrxxBean.getLoanRole())) {

                    dkrxxBean.setLoanRoleName(list.get(i).getLoanRoleName());

                    list.remove(i);
                    list.add(i, dkrxxBean);

                }

            }

        }

        if (isDetail) {
            Iterator<DkrxxBean> iterator = list.iterator();
            while(iterator.hasNext()){
                DkrxxBean bean = iterator.next();
                if (TextUtils.isEmpty(bean.getMobile())){
                    iterator.remove();
                }
            }

            mBinding.btnConfirm.setVisibility(View.GONE);
        }


        mAdapter.notifyDataSetChanged();


    }

    private void doRequest() {

        List<DkrxxBean> upLits = new ArrayList<>();
        for (DkrxxBean bean : list) {
            if (!TextUtils.isEmpty(bean.getMobile())) {
                upLits.add(bean);
            }
        }

        HashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("code", ((ZrzlActivity) mActivity).code);
        hashMap.put("creditUserList", upLits);
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("type", "android");

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632530", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("1"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }

    @Subscribe
    public void dkrxx(EventBean bean) {

        if (bean.getTag().equals("dkrxx")) {

            int position = Integer.parseInt(bean.getValue1());
            list.remove(position);
            list.add(position, (DkrxxBean) bean.getValue());
            mAdapter.notifyDataSetChanged();

        }

    }

}
