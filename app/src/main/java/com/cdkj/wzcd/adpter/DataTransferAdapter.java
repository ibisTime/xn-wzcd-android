package com.cdkj.wzcd.adpter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemDataTransferBinding;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.module.datatransfer.SendActivity;
import com.cdkj.wzcd.module.datatransfer.SendAndExamineActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class DataTransferAdapter extends BaseQuickAdapter<DataTransferModel, BaseViewHolder> {

    private ItemDataTransferBinding mBinding;
    private List<DataDictionary> mCompany;

    public DataTransferAdapter(@Nullable List<DataTransferModel> data, List<DataDictionary> company) {
        super(R.layout.item_data_transfer, data);

        mCompany = company;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataTransferModel item) {

        mBinding  = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getBizCode(), "");

        mBinding.myIlFrom.setText(NodeHelper.getNameOnTheCode(item.getFromNodeCode()));
        mBinding.myIlTo.setText(NodeHelper.getNameOnTheCode(item.getToNodeCode()));

        mBinding.myIlName.setText(item.getUserName());
        mBinding.myIlCompany.setText(DataDictionaryHelper.getValueBuyList(item.getLogisticsCompany(), mCompany));
        mBinding.myIlExpress.setText(item.getLogisticsCode());


        mBinding.myItemCblConfirm.setContent("", "");
        mBinding.myIlStatus.setText(getStatus(item));


    }

    private String getStatus(DataTransferModel item){
        // 状态(0 待发件 1已发件待收件 2已收件审核 3已收件待补件)

        switch (item.getStatus()){

            case "0":
                mBinding.myItemCblConfirm.setRightTextAndListener("发件", view -> {
                    //发件
                    SendActivity.open(mContext, item.getCode());
                });
                return "待发件";

            case "1":

                mBinding.myItemCblConfirm.setRightTextAndListener("收件并审核", view -> {
                    //收件并审核
                    SendAndExamineActivity.open(mContext, item.getCode());
                });
                return "已发件待收件";

            case "2":
                return "已收件审核";

            case "3":
                return "已收件待补件";

            default:
                return "";

        }
    }
}
