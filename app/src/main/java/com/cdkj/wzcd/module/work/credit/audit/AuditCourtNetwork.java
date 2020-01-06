package com.cdkj.wzcd.module.work.credit.audit;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityAuditCourtNetworkBinding;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.io.Serializable;
import java.util.List;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * 法院网查询结果录入
 */
public class AuditCourtNetwork extends AbsBaseLoadActivity {
    private ActivityAuditCourtNetworkBinding mBinding;
    private CreditUserModel mResult;
    private int postion;
    private List<DataDictionary> role;
    private List<DataDictionary> relation;


    public static void open(Context context, CreditUserModel model, int postion, List<DataDictionary> role, List<DataDictionary> relation) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, AuditCourtNetwork.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("postion", postion);
        intent.putExtra("role", (Serializable) role);
        intent.putExtra("relation", (Serializable) relation);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        //        activity_audit_court_network
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_audit_court_network, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入法院网查询结果");

        init();
        initOnclick();
        setView();
    }

    private void init() {
        if (getIntent() != null) {
            mResult = (CreditUserModel) getIntent().getSerializableExtra(DATA_SIGN);
            postion = getIntent().getIntExtra("postion", -1);
            role = (List<DataDictionary>) getIntent().getSerializableExtra("role");
            relation = (List<DataDictionary>) getIntent().getSerializableExtra("relation");
        }

    }

    private void initOnclick() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            String mesg = mBinding.myElMessage.getText();
            if (TextUtils.isEmpty(mesg)) {
                UITipDialog.showInfo(this, "请填入法院网查询结果");
                return;
            }
            if (AuditOneActivity.mList.size() > 0) {
                AuditOneActivity.mList.get(postion).setCourtNetworkMesg(mesg);
            }
            finish();
        });
    }

    private void setView() {
        if (mResult == null) {
            //没有数据  也不能显示出按
            return;
        }


        mBinding.myElUserName.setTextByRequest(mResult.getUserName());
//        mBinding.myElUserContact.setTextByRequest(mResult.getUserName());//贷款人与本人关系
        mBinding.myElUserContact.setTextByRequest(DataDictionaryHelper.getValueBuyList(mResult.getRelation(), relation));//贷款人与本人关系
//        mBinding.myElUserType.setTextByRequest(mResult.getUserName());//贷款角色
        mBinding.myElUserType.setTextByRequest(DataDictionaryHelper.getValueBuyList(mResult.getLoanRole(), role));
        mBinding.myElUserPhone.setTextByRequest(mResult.getMobile());//手机号
        mBinding.myElUserId.setTextByRequest(mResult.getIdNo());//身份证号

        String isFirstAudit = mResult.getIsFirstAudit();

        mBinding.myElIsFirstInstance.setTextByRequest(TextUtils.equals(isFirstAudit, "1") ? "是" : "否");

        mBinding.myIlIdCard.setFlImgByRequest(mResult.getIdNoFront());
        mBinding.myIlIdCard.setFlImgRightByRequest(mResult.getIdNoReverse());
        mBinding.myIlFace.setFlImgByRequest(mResult.getInterviewPic());
        mBinding.myIlZxcx.setFlImgByRequest(mResult.getAuthPdf());


        mBinding.myElDkdyCount.setTextByRequest(mResult.getDkdyCount()+"");
        mBinding.myElDkdyAmount.setTextByRequest(mResult.getDkdyAmount());
        mBinding.myElDkdy2yearOverTimes.setTextByRequest(mResult.getDkdy2YearOverTimes());
        mBinding.myElDkdyMaxOverAmount.setTextByRequest(mResult.getDkdyMaxOverAmount());
        mBinding.myElDkdyCurrentOverAmount.setTextByRequest(mResult.getDkdyCurrentOverAmount());
        mBinding.myElDkdy6monthAvgAmount.setTextByRequest(mResult.getDkdy6MonthAvgAmount());


        mBinding.myElHkxyUnsettleCount.setTextByRequest(mResult.getHkxyUnsettleCount()+"");
        mBinding.myElHkxyUnsettleAmount.setTextByRequest(mResult.getHkxyUnsettleAmount());
        mBinding.myElHkxy2yearOverTimes.setTextByRequest(mResult.getHkxy2YearOverTimes());
        mBinding.myElHkxyMonthMaxOverAmount.setTextByRequest(mResult.getHkxyMonthMaxOverAmount());
        mBinding.myElHkxyCurrentOverAmount.setTextByRequest(mResult.getHkxyCurrentOverAmount());
        mBinding.myElHkxy6monthAvgAmount.setTextByRequest(mResult.getHkxy6MonthAvgAmount());

        mBinding.myElXykCount.setTextByRequest(mResult.getXykCount()+"");
        mBinding.myElXykCreditAmount.setTextByRequest(mResult.getXykCreditAmount());
        mBinding.myElXyk6monthUseAmount.setTextByRequest(mResult.getXyk6MonthUseAmount());
        mBinding.myElXyk2yearOverTimes.setTextByRequest(mResult.getXyk2YearOverTimes());
        mBinding.myElXykMonthMaxOverAmount.setTextByRequest(mResult.getXykMonthMaxOverAmount());
        mBinding.myElXykCurrentOverAmount.setTextByRequest(mResult.getXykCurrentOverAmount());

        mBinding.myElOutGuaranteesCount.setTextByRequest(mResult.getOutGuaranteesCount()+"");
        mBinding.myElOutGuaranteesAmount.setTextByRequest(mResult.getOutGuaranteesAmount());
        mBinding.myElOutGuaranteesRemark.setTextByRequest(mResult.getOutGuaranteesRemark());

    }


}
