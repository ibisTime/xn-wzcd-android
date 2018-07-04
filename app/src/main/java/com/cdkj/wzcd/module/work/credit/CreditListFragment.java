package com.cdkj.wzcd.module.work.credit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adapter.CreditListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.UserHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.IS_FIRST_REQUEST;

/**
 * Created by cdkj on 2018/5/29.
 */

public class CreditListFragment extends AbsRefreshListFragment {

    private boolean isFirstRequest;

    // 购车途径
    private List<DataDictionary> mType = new ArrayList<>();

    /**
     * @param
     * @return
     */
    public static CreditListFragment getInstance(Boolean isFirstRequest, String status) {
        CreditListFragment fragment = new CreditListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, status);
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
            isFirstRequest = getArguments().getBoolean(IS_FIRST_REQUEST);

            initRefreshHelper(MyCdConfig.LIST_LIMIT);

            if (isFirstRequest){
                mRefreshHelper.onDefaultMRefresh(true);
            }

        }
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {

        CreditListAdapter mAdapter = new CreditListAdapter(listData, mType);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditModel model = mAdapter.getItem(position);

            CreditDetailActivity.open(mActivity, model.getCode(), false);
        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {

        List<DataDictionary> list = DataDictionaryHelper.getListByParentKey(DataDictionaryHelper.budget_orde_biz_typer);

        if (list == null || list.size() == 0){
            return;
        }
        mType.addAll(list);

        Map<String, String> map = new HashMap<>();

        map.put("roleCode", SPUtilHelper.getRoleCode());
        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        if (UserHelper.isYWY())
            map.put("saleUserId", SPUtilHelper.getUserId());



        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCreditList("632115", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CreditModel>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<CreditModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无资信", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }
}
