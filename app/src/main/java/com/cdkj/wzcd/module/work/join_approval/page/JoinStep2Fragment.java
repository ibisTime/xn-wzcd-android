package com.cdkj.wzcd.module.work.join_approval.page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep2Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.view.interfaces.MyFrontSelectInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_relation;
import static com.cdkj.wzcd.util.DataDictionaryHelper.marry_state;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep2Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep2Binding mBinding;

    private NodeListModel data;

    public static JoinStep2Fragment getInstance(String code) {
        JoinStep2Fragment fragment = new JoinStep2Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step2, null, false);

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
        //贷款人与共还人的关系不可更改
        mBinding.mySlApplyUserGhrRelation.setData(DataDictionaryHelper.getListByParentKey(credit_user_relation), (MyFrontSelectInterface) () -> true, null);
        mBinding.mySlMarryState.setData(DataDictionaryHelper.getListByParentKey(marry_state), null);

        mBinding.mySlApplyUserJourShowIncome.setDataIs(null);

        // 申请人一定打件
        mBinding.mySlApplyUserIsPrint.setTextAndKey("1", "是");
        mBinding.mySlApplyUserIsPrint.setOnClickEnable(false);

        mBinding.mySlGhJourShowIncome.setDataIs(null);
        mBinding.mySlGhIsPrint.setDataIs(null);

        mBinding.mySlGuarantor1JourShowIncome.setDataIs(null);
        mBinding.mySlGuarantor1IsPrint.setDataIs(null);

        mBinding.mySlGuarantor2JourShowIncome.setDataIs(null);
        mBinding.mySlGuarantor2IsPrint.setDataIs(null);

    }

    private void setView() {


        if (((JoinApplyActivity) mActivity).isDetails) {

            mBinding.myNlApplyUserName.setText(data.getCustomerName());//贷款人
            mBinding.myNlGhUserName.setText(TextUtils.isEmpty(data.getGhRealName()) ? "暂无共换人" : data.getGhRealName());//共换人
            mBinding.myNlDanbaoName.setText(TextUtils.isEmpty(data.getGuarantor1Name()) ? "暂无担保人" : data.getGuarantor1Name());//担保人  可能有多个  可能没有  只取一个
            mBinding.myElApplyUserCompany.setTextByRequest(data.getApplyUserCompany());


            mBinding.myElApplyUserDuty.setTextByRequest(data.getApplyUserDuty());
            mBinding.mySlApplyUserGhrRelation.setTextByRequestByKey(data.getApplyUserGhrRelation());
            mBinding.mySlMarryState.setTextByRequestByKey(data.getMarryState());

            mBinding.myElApplyUserMonthIncome.setMoneyTextRequest(data.getApplyUserMonthIncome());
            mBinding.myElApplyUserSettleInterest.setMoneyTextRequest(data.getApplyUserSettleInterest());
            mBinding.myElApplyUserBalance.setMoneyTextRequest(data.getApplyUserBalance());
            mBinding.mySlApplyUserJourShowIncome.setTextByRequestByKey(data.getApplyUserJourShowIncome());
            mBinding.mySlApplyUserIsPrint.setTextByRequestByKey(data.getApplyUserIsPrint());

            mBinding.myElGhMonthIncome.setMoneyTextRequest(data.getGhMonthIncome());
            mBinding.myElGhSettleInterest.setMoneyTextRequest(data.getGhSettleInterest());
            mBinding.myElGhBalance.setMoneyTextRequest(data.getGhBalance());
            mBinding.mySlGhJourShowIncome.setTextByRequestByKey(data.getGhJourShowIncome());
            mBinding.mySlGhIsPrint.setTextByRequestByKey(data.getGhIsPrint());

            mBinding.myNlGuarantor1UserName.setText(data.getApplyUserCompany());
            mBinding.myElGuarantor1MonthIncome.setMoneyTextRequest(data.getGuarantor1MonthIncome());
            mBinding.myElGuarantor1SettleInterest.setMoneyTextRequest(data.getGuarantor1SettleInterest());
            mBinding.myElGuarantor1Balance.setMoneyTextRequest(data.getGuarantor1Balance());
            mBinding.mySlGuarantor1JourShowIncome.setTextByRequestByKey(data.getGuarantor1JourShowIncome());
            mBinding.mySlGuarantor1IsPrint.setTextByRequestByKey(data.getGuarantor1IsPrint());

            mBinding.myNlGuarantor2UserName.setText(data.getApplyUserCompany());
            mBinding.myElGuarantor2MonthIncome.setMoneyTextRequest(data.getGuarantor2MonthIncome());
            mBinding.myElGuarantor2SettleInterest.setMoneyTextRequest(data.getGuarantor2SettleInterest());
            mBinding.myElGuarantor2Balance.setMoneyTextRequest(data.getGuarantor2Balance());
            mBinding.mySlGuarantor2JourShowIncome.setTextByRequestByKey(data.getGuarantor2JourShowIncome());
            mBinding.mySlGuarantor2IsPrint.setTextByRequestByKey(data.getGuarantor2IsPrint());

            mBinding.myElOtherIncomeNote.setTextByRequest(data.getOtherIncomeNote());
            mBinding.myElOtherIncomeNote.setTextHint("");

        } else {
            mBinding.myNlApplyUserName.setText(data.getCustomerName());//贷款人
            mBinding.myNlGhUserName.setText(data.getGhRealName());//共换人
            mBinding.myNlDanbaoName.setText(TextUtils.isEmpty(data.getGuarantor1Name()) ? "暂无担保人" : data.getGuarantor1Name());//担保人  可能有多个  可能没有  只取一个
            mBinding.myElApplyUserCompany.setText(data.getApplyUserCompany());


            mBinding.myElApplyUserDuty.setText(data.getApplyUserDuty());
            mBinding.mySlApplyUserGhrRelation.setContentByKey(data.getApplyUserGhrRelation());
            mBinding.mySlMarryState.setContentByKey(data.getMarryState());

            mBinding.myElApplyUserMonthIncome.setText(data.getApplyUserMonthIncome());
            mBinding.myElApplyUserSettleInterest.setText(data.getApplyUserSettleInterest());
            mBinding.myElApplyUserBalance.setText(data.getApplyUserBalance());
            mBinding.mySlApplyUserJourShowIncome.setContentByKey(data.getApplyUserJourShowIncome());
            mBinding.mySlApplyUserIsPrint.setContentByKey(data.getApplyUserIsPrint());

            mBinding.myElGhMonthIncome.setText(data.getGhMonthIncome());
            mBinding.myElGhSettleInterest.setText(data.getGhSettleInterest());
            mBinding.myElGhBalance.setText(data.getGhBalance());
            mBinding.mySlGhJourShowIncome.setContentByKey(data.getGhJourShowIncome());
            mBinding.mySlGhIsPrint.setContentByKey(data.getGhIsPrint());

            mBinding.myNlGuarantor1UserName.setText(data.getApplyUserCompany());
            mBinding.myElGuarantor1MonthIncome.setText(data.getGuarantor1MonthIncome());
            mBinding.myElGuarantor1SettleInterest.setText(data.getGuarantor1SettleInterest());
            mBinding.myElGuarantor1Balance.setText(data.getGuarantor1Balance());
            mBinding.mySlGuarantor1JourShowIncome.setContentByKey(data.getGuarantor1JourShowIncome());
            mBinding.mySlGuarantor1IsPrint.setContentByKey(data.getGuarantor1IsPrint());

            mBinding.myNlGuarantor2UserName.setText(data.getApplyUserCompany());
            mBinding.myElGuarantor2MonthIncome.setText(data.getGuarantor2MonthIncome());
            mBinding.myElGuarantor2SettleInterest.setText(data.getGuarantor2SettleInterest());
            mBinding.myElGuarantor2Balance.setText(data.getGuarantor2Balance());
            mBinding.mySlGuarantor2JourShowIncome.setContentByKey(data.getGuarantor2JourShowIncome());
            mBinding.mySlGuarantor2IsPrint.setContentByKey(data.getGuarantor2IsPrint());

            mBinding.myElOtherIncomeNote.setText(data.getOtherIncomeNote());
        }

    }

    private String checkFail;

    public boolean check() {
        if (mBinding.myElApplyUserCompany.check()) {
            checkFail = mBinding.myElApplyUserCompany.getTitle();
            return false;
        }

        if (mBinding.myElApplyUserDuty.check()) {
            checkFail = mBinding.myElApplyUserDuty.getTitle();
            return false;
        }

        if (mBinding.mySlApplyUserGhrRelation.check()) {
            checkFail = mBinding.mySlApplyUserGhrRelation.getTitle();
            return false;
        }

        if (mBinding.mySlMarryState.check()) {
            checkFail = mBinding.mySlMarryState.getTitle();
            return false;
        }

        if (mBinding.myElApplyUserMonthIncome.check()) {
            checkFail = mBinding.myElApplyUserMonthIncome.getTitle();
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

        if (model.getCheckIndex() == 1) {
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

        map.put("applyUserCompany", mBinding.myElApplyUserCompany.getText());
        map.put("applyUserDuty", mBinding.myElApplyUserDuty.getText());
        map.put("applyUserGhrRelation", mBinding.mySlApplyUserGhrRelation.getDataKey());
        map.put("marryState", mBinding.mySlMarryState.getDataKey());

        map.put("applyUserMonthIncome", mBinding.myElApplyUserMonthIncome.getText());
        map.put("applyUserSettleInterest", mBinding.myElApplyUserSettleInterest.getText());
        map.put("applyUserBalance", mBinding.myElApplyUserBalance.getText());
        map.put("applyUserJourShowIncome", mBinding.mySlApplyUserJourShowIncome.getDataKey());
        map.put("applyUserIsPrint", mBinding.mySlApplyUserIsPrint.getDataKey());

        map.put("ghMonthIncome", mBinding.myElGhMonthIncome.getText());
        map.put("ghSettleInterest", mBinding.myElGhSettleInterest.getText());
        map.put("ghBalance", mBinding.myElGhBalance.getText());
        map.put("ghJourShowIncome", mBinding.mySlGhJourShowIncome.getDataKey());
        map.put("ghIsPrint", mBinding.mySlGhIsPrint.getDataKey());

        map.put("guarantor1MonthIncome", mBinding.myElGuarantor1MonthIncome.getText());
        map.put("guarantor1SettleInterest", mBinding.myElGuarantor1SettleInterest.getText());
        map.put("guarantor1Balance", mBinding.myElGuarantor1Balance.getText());
        map.put("uarantor1JourShowIncome", mBinding.mySlGuarantor1JourShowIncome.getDataKey());
        map.put("guarantor1IsPrint", mBinding.mySlGuarantor1IsPrint.getDataKey());

        map.put("guarantor2MonthIncome", mBinding.myElGuarantor2MonthIncome.getText());
        map.put("guarantor2SettleInterest", mBinding.myElGuarantor2SettleInterest.getText());
        map.put("guarantor2Balance", mBinding.myElGuarantor2Balance.getText());
        map.put("guarantor2JourShowIncome", mBinding.mySlGuarantor2JourShowIncome.getDataKey());
        map.put("guarantor2IsPrint", mBinding.mySlGuarantor2IsPrint.getDataKey());

        map.put("otherIncomeNote", mBinding.myElOtherIncomeNote.getText());

        return map;
    }
}
