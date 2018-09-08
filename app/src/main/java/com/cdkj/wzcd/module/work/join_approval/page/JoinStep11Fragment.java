package com.cdkj.wzcd.module.work.join_approval.page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep11Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_relation;

/**
 * Created by cdkj on 2018/6/7.
 * 紧急联系人
 */

public class JoinStep11Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep11Binding mBinding;

    private NodeListModel data;

    public static JoinStep11Fragment getInstance(String code) {
        JoinStep11Fragment fragment = new JoinStep11Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step11, null, false);

        if (getArguments() != null) {
            code = getArguments().getString(DATA_SIGN);
        }

        data = ((JoinApplyActivity) mActivity).mData;

        initView();
        setView();

        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initView() {
        mBinding.mySl110Relationship1.setData(DataDictionaryHelper.getListByParentKey(credit_user_relation), null);
        mBinding.mySl110Relationship2.setData(DataDictionaryHelper.getListByParentKey(credit_user_relation), null);

    }

    private void setView() {
        if (((JoinApplyActivity) mActivity).isDetails) {
            mBinding.myEl110ContactName1.setTextByRequest(data.getEmergencyName1());
            mBinding.mySl110Relationship1.setTextByRequestByKey(data.getEmergencyRelation1());
            mBinding.myEl110ContactNumber1.setTextByRequest(data.getEmergencyMobile1());

            mBinding.myEl110ContactName2.setTextByRequest(data.getEmergencyName2());
            mBinding.mySl110Relationship2.setTextByRequestByKey(data.getEmergencyRelation2());
            mBinding.myEl110ContactNumber2.setTextByRequest(data.getEmergencyMobile2());

            mBinding.myEl110ContactName1.setTextHint("");
            mBinding.myEl110ContactNumber1.setTextHint("");
            mBinding.myEl110ContactName2.setTextHint("");
            mBinding.myEl110ContactNumber2.setTextHint("");
        } else {

            mBinding.myEl110ContactName1.setText(data.getEmergencyName1());
            mBinding.mySl110Relationship1.setContentByKey(data.getEmergencyRelation1());
            mBinding.myEl110ContactNumber1.setText(data.getEmergencyMobile1());

            mBinding.myEl110ContactName2.setText(data.getEmergencyName2());
            mBinding.mySl110Relationship2.setContentByKey(data.getEmergencyRelation2());
            mBinding.myEl110ContactNumber2.setText(data.getEmergencyMobile2());
        }


    }

    private String checkFail;

    public boolean check() {

        if (mBinding.myEl110ContactName1.check()) {
            checkFail = mBinding.myEl110ContactName1.getTitle();
            return false;
        }
        if (mBinding.mySl110Relationship1.check()) {
            checkFail = mBinding.mySl110Relationship1.getTitle();
            return false;
        }
        if (mBinding.myEl110ContactNumber1.check()) {
            checkFail = mBinding.myEl110ContactNumber1.getTitle();
            return false;
        }


        return true;
    }

    @Subscribe
    public void doCheck(BudgetCheckModel model) {

        if (model == null)
            return;

        // 检查未通过，由Activity提示，不往下check
        if (!model.isCheckResult()) {
            return;
        }

        if (model.getCheckIndex() == 3) {
            if (check()) {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex() + 1)
                        .setCheckResult(true)
                        .setCheckFail(null));

            } else {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex())
                        .setCheckResult(false)
                        .setCheckFail(checkFail));

            }
        }

    }

    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<>();


        map.put("emergencyName1", mBinding.myEl110ContactName1.getText());
        map.put("emergencyRelation1", mBinding.mySl110Relationship1.getDataKey());
        map.put("emergencyMobile1", mBinding.myEl110ContactNumber1.getText());

        map.put("emergencyName2", mBinding.myEl110ContactName2.getText());
        map.put("emergencyRelation2", mBinding.mySl110Relationship2.getDataKey());
        map.put("emergencyMobile2", mBinding.myEl110ContactNumber2.getText());


        return map;
    }
}
