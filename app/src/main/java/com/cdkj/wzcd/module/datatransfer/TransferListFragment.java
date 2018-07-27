package com.cdkj.wzcd.module.datatransfer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adapter.DataTransferAdapter;
import com.cdkj.wzcd.adapter.GPSTransferAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.CllhListBean;
import com.cdkj.wzcd.model.DataTransferModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.IS_FIRST_REQUEST;

public class TransferListFragment extends AbsRefreshListFragment<CllhListBean> {

    public static final String DATA_SEND = "data_send";
    public static final String DATA_RECEIVE = "data_receive";
    public static final String GPS_SEND = "gps_send";
    public static final String GPS_RECEIVE = "gps_receive";

    private String dataType;
    private boolean isFirstRequest;

    /**
     * @param
     * @return
     */
    public static TransferListFragment getInstance(Boolean isFirstRequest, String dataType) {
        TransferListFragment fragment = new TransferListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, dataType);
        bundle.putBoolean(IS_FIRST_REQUEST, isFirstRequest);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()){
            mRefreshHelper.onDefaultMRefresh(true);
        }
    }

    @Override
    protected void lazyLoad() {
        if (mRefreshBinding != null){
            mRefreshHelper.onDefaultMRefresh(true);
        }

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void afterCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            dataType = getArguments().getString(DATA_SIGN);
            isFirstRequest = getArguments().getBoolean(IS_FIRST_REQUEST);

            initRefreshHelper(MyCdConfig.LIST_LIMIT);

            if (isFirstRequest){
                mRefreshHelper.onDefaultMRefresh(true);
            }

        }
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {


        if (TextUtils.equals(dataType, DATA_SEND) || TextUtils.equals(dataType, DATA_RECEIVE)){
            DataTransferAdapter mAdapter = new DataTransferAdapter(listData, this);

            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                DataTransferModel model = mAdapter.getItem(position);

                pickUpRequest(model.getCode());
            });

            return mAdapter;
        }else {
            GPSTransferAdapter mAdapter = new GPSTransferAdapter(listData);

            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            });

            return mAdapter;

        }


    }


    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {

        Map<String, Object> map = new HashMap<>();

        List<String> statusList = new ArrayList<>();

        if (TextUtils.equals(dataType, DATA_SEND)){
            map.put("type", "1");
            statusList.add("0");
            statusList.add("4");

        }else if (TextUtils.equals(dataType, DATA_RECEIVE)){
            map.put("type", "1");
            statusList.add("1");
            statusList.add("2");
            statusList.add("3");
            statusList.add("5");
            statusList.add("6");

        }else if (TextUtils.equals(dataType, GPS_SEND)){
            map.put("type", "2");
            statusList.add("0");
            statusList.add("4");

        }else {
            map.put("type", "2");
            statusList.add("1");
            statusList.add("6");

        }

        map.put("statusList", statusList);
        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getDataTransfer("632155", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<DataTransferModel>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<DataTransferModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无资料", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }


    /**
     * 收件
     */
    public void pickUpRequest(String code) {
        if (TextUtils.isEmpty(code))
            return;

        List<String> codeList = new ArrayList<>();
        codeList.add(code);

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("codeList", codeList);
        hashMap.put("operator", SPUtilHelper.getUserId());
//        hashMap.put("remark", mBinding.myElSendNote.getText());

        Call call= RetrofitUtils.getBaseAPiService().successRequest("632151", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(mActivity, "操作成功", dialogInterface -> {
                        mRefreshHelper.onDefaultMRefresh(true);
                    });
                } else {
                    UITipDialog.showFail(mActivity, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

}
