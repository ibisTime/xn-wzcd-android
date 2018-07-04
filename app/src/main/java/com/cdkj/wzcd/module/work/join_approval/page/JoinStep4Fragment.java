package com.cdkj.wzcd.module.work.join_approval.page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep4Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep4Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep4Binding mBinding;

    private NodeListModel data;

    public static JoinStep4Fragment getInstance(String code) {
        JoinStep4Fragment fragment = new JoinStep4Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step4, null, false);

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

        mBinding.mySlHouseType.setData("0", "自有", "1", "租用", null);

    }

    private void setView() {

        mBinding.myElApplyBirthAddress.setText(data.getApplyBirthAddress());
        mBinding.myElApplyNowAddress.setText(data.getApplyNowAddress());

        mBinding.mySlHouseType.setContentByKey(data.getHouseType());

        mBinding.myElGhBirthAddress.setText(data.getGhBirthAddress());
        mBinding.myElGuarantor1BirthAddress.setText(data.getGuarantor1BirthAddress());
        mBinding.myElGuarantor2BirthAddress.setText(data.getGuarantor2BirthAddress());
        mBinding.myElOtherNote.setText(data.getOtherNote());
    }

    private String checkFail;

    public boolean check(){
        if (mBinding.myElApplyBirthAddress.check()){
            checkFail = mBinding.myElApplyBirthAddress.getTitle();
            return false;
        }

        if (mBinding.myElApplyNowAddress.check()){
            checkFail = mBinding.myElApplyNowAddress.getTitle();
            return false;
        }

        if (mBinding.mySlHouseType.check()){
            checkFail = mBinding.mySlHouseType.getTitle();
            return false;
        }

        if (mBinding.myElGhBirthAddress.check()){
            checkFail = mBinding.myElGhBirthAddress.getTitle();
            return false;
        }

        if (mBinding.myElGuarantor1BirthAddress.check()){
            checkFail = mBinding.myElGuarantor1BirthAddress.getTitle();
            return false;
        }

        if (mBinding.myElGuarantor2BirthAddress.check()){
            checkFail = mBinding.myElGuarantor2BirthAddress.getTitle();
            return false;
        }

        return true;
    }

    @Subscribe
    public void doCheck(BudgetCheckModel model){

        if (model == null)
            return;

        // 检查未通过，由Activity提示，不往下check
        if (!model.isCheckResult()){
            return;
        }

        if (model.getCheckIndex() == 3){
            if (check()){

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex()+1)
                        .setCheckResult(true)
                        .setCheckFail(null));

            }else {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex())
                        .setCheckResult(false)
                        .setCheckFail(checkFail));

            }
        }
    }

    public Map<String, Object> getData(){

        Map<String, Object> map = new HashMap<>();

        map.put("applyBirthAddress",mBinding.myElApplyBirthAddress.getText());
        map.put("applyNowAddress",mBinding.myElApplyNowAddress.getText());
        map.put("houseType",mBinding.mySlHouseType.getDataKey());
        map.put("ghBirthAddress",mBinding.myElGhBirthAddress.getText());
        map.put("guarantor1BirthAddress",mBinding.myElGuarantor1BirthAddress.getText());
        map.put("guarantor2BirthAddress",mBinding.myElGuarantor2BirthAddress.getText());

        map.put("otherNote",mBinding.myElOtherNote.getText());

        return map;
    }
}
