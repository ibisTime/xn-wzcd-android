package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemGpsTransferBinding;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.module.datatransfer.GpsReceiveActivity;
import com.cdkj.wzcd.module.datatransfer.GpsSendActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.wzcd.util.DataDictionaryHelper.send_type;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class GPSTransferAdapter extends BaseQuickAdapter<DataTransferModel, BaseViewHolder> {

    private ItemGpsTransferBinding mBinding;

    public GPSTransferAdapter(@Nullable List<DataTransferModel> data) {
        super(R.layout.item_gps_transfer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataTransferModel item) {

        mBinding  = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemCblConfirm.setContent("", "");

        mBinding.myTlIdStatus.setText(item.getBizCode(), getStatus(item));

        mBinding.myIlName.setText(item.getUserName());
        mBinding.myIlSendType.setText(DataDictionaryHelper.getValueBuyKey(send_type, item.getSendType()));


//        mBinding.myIlFrom.setText(NodeHelper.getNameOnTheCode(item.getFromNodeCode()));
//        mBinding.myIlTo.setText(NodeHelper.getNameOnTheCode(item.getToNodeCode()));

        mBinding.myIlCompany.setText(DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.kd_company, item.getLogisticsCompany()));
        mBinding.myIlExpress.setText(item.getLogisticsCode());

        mBinding.myIlSendNote.setText(item.getSendNote());
//        mBinding.myIlSupplementReason.setText(item.getSupplementReason());
    }

    private String getStatus(DataTransferModel item){
        // 状态(0 待发件 1已发件待收件 2已收件审核 3已收件待补件)

//        switch (item.getStatus()){
//
//            case "0":
//                mBinding.myItemCblConfirm.setRightTextAndListener("发件", view -> {
//                    //发件
//                    GpsSendActivity.open(mContext, item.getCode());
//                });
//                return "待发件";
//
//            case "1":
//                mBinding.myItemCblConfirm.setRightTextAndListener("收件", view -> {
//                    //收件并审核
//                    DataReceiveActivity.open(mContext, item.getCode());
//                });
//                return "已发件待收件";
//
//            case "2":
//                return "已收件审核";
//
//            case "3":
//                mBinding.myItemCblConfirm.setRightTextAndListener("补件", view -> {
//                    //收件并审核
//                    .open(mContext, item.getCode());
//                });
//
//                return "已收件待补件";
//
//            default:
//                return "";
//
//        }

        switch (item.getStatus()){

            case "0":
                mBinding.myItemCblConfirm.setRightTextAndListener("发件", view -> {
                    //发件
                    GpsSendActivity.open(mContext, item.getCode(), true);
                });
                return "待发件";

            case "1":
                mBinding.myItemCblConfirm.setRightTextAndListener("收件", view -> {
                    //收件并审核
                    GpsReceiveActivity.open(mContext, item.getCode());
                });
                return "已发件待收件";

            case "2":
                return "待审核";

            case "3":

                return "审核通过";

            case "4":
                mBinding.myItemCblConfirm.setRightTextAndListener("补件", view -> {
                    //补件
                    GpsSendActivity.open(mContext, item.getCode(), false);
                });

                return "待补件";

            case "5":

                return "退件";

            case "6":

                return "已收件";

            default:
                return "";

        }
    }
}
