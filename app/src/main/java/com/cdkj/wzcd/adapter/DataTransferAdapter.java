package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemDataTransferBinding;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.module.datatransfer.DataReceiveActivity;
import com.cdkj.wzcd.module.datatransfer.DataSendActivity;
import com.cdkj.wzcd.module.datatransfer.DataSupplementActivity;
import com.cdkj.wzcd.module.datatransfer.TransferListFragment;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.wzcd.util.DataDictionaryHelper.logistics_type;
import static com.cdkj.wzcd.util.DataDictionaryHelper.send_type;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class DataTransferAdapter extends BaseQuickAdapter<DataTransferModel, BaseViewHolder> {

    private TransferListFragment mFragment;
    private ItemDataTransferBinding mBinding;

    public DataTransferAdapter(@Nullable List<DataTransferModel> data, TransferListFragment fragment) {
        super(R.layout.item_data_transfer, data);
        mFragment = fragment;

    }

    @Override
    protected void convert(BaseViewHolder helper, DataTransferModel item) {

        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemCblConfirm.setContent("", "");

        mBinding.myTlIdStatus.setText(item.getBizCode(), getStatus(helper, item));

        mBinding.myIlType.setText(DataDictionaryHelper.getValueBuyKey(logistics_type, item.getType()));
        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlSendType.setText(DataDictionaryHelper.getValueBuyKey(send_type, item.getSendType()));

        if (TextUtils.equals(item.getSendType(), "1")) {
            mBinding.myIlCompany.setVisibility(View.GONE);
            mBinding.myIlExpress.setVisibility(View.GONE);
        }

        mBinding.myIlCompany.setText(DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.kd_company, item.getLogisticsCompany()));
        mBinding.myIlExpress.setText(item.getLogisticsCode());

        mBinding.myIlFrom.setText(NodeHelper.getNameOnTheCode(item.getToNodeCode()));
        mBinding.myIlTo.setText(NodeHelper.getNameOnTheCode(item.getFromNodeCode()));

    }

    private String getStatus(BaseViewHolder helper, DataTransferModel item) {

        switch (item.getStatus()) {

            case "0":
                mBinding.myItemCblConfirm.setRightTextAndListener("发件", view -> {
                    //发件
                    DataSendActivity.open(mContext, item.getCode());
                });
                return "待发件";

            case "1":
                mBinding.myItemCblConfirm.setRightTextAndListener("收件", view -> {
                    //收件并审核
                    mFragment.pickUpRequest(item.getCode());
                });

                return "已发件待收件";

            case "2":
                mBinding.myItemCblConfirm.setRightTextAndListener("审核", view -> {
                    //审核
                    DataReceiveActivity.open(mContext, item.getCode());
                });
                return "待审核";

            case "3":

                return "审核通过";

            case "4":
                mBinding.myItemCblConfirm.setRightTextAndListener("补件", view -> {
                    //补件
                    DataSupplementActivity.open(mContext, item.getCode());
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
