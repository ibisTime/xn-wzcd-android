package com.cdkj.wzcd.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.interfaces.BaseRefreshCallBack;
import com.cdkj.baselibrary.interfaces.RefreshHelper;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.MainFrgClientBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.adapter.CreditPageAdapter;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:31
 */
public class MainClientFragment extends BaseLazyFragment {

    private MainFrgClientBinding mBinding;

    private String keyword = "";
    protected RefreshHelper mRefreshHelper;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MainClientFragment getInstance() {
        MainClientFragment fragment = new MainClientFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_frg_client, null, false);

        initListener();
        initRefreshHelper(10);

        return mBinding.getRoot();
    }

    private void initListener() {
        mBinding.tvSearch.setOnClickListener(v -> {
//            if (TextUtils.isEmpty(mBinding.etSearch.getText().toString())) {
//                ToastUtil.show(mActivity, "请输入客户姓名，手机号，身份证号");
//                return;
//            }

            keyword = mBinding.etSearch.getText().toString();
            mRefreshHelper.onDefaultMRefresh(true);

        });
    }

    /**
     * 初始化刷新相关
     */
    protected void initRefreshHelper(int limit) {
        mRefreshHelper = new RefreshHelper(mActivity, new BaseRefreshCallBack(mActivity) {
            @Override
            public View getRefreshLayout() {
                return mBinding.refreshLayout;
            }

            @Override
            public RecyclerView getRecyclerView() {
                return mBinding.rv;
            }

            @Override
            public RecyclerView.Adapter getAdapter(List listData) {
                return getListAdapter(listData);
            }

            @Override
            public void getListDataRequest(int pageIndex, int limit, boolean isShowDialog) {
                getListRequest(pageIndex, limit, isShowDialog);
            }
        });
        mRefreshHelper.init(limit);
        mRefreshHelper.onDefaultMRefresh(true);
    }


    public RecyclerView.Adapter getListAdapter(List listData) {
        CreditPageAdapter mAdapter = new CreditPageAdapter(listData, null);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            CreditPageBean item = mAdapter.getItem(position);
            CreditActivity.open(mActivity, item.getCode());

        });
        return mAdapter;
    }


    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, Object> map = RetrofitUtils.getNodeListMap();

        map.put("keyword", keyword);
        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("isMy", "1");

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCreditPage("632515", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CreditPageBean>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<CreditPageBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无客户", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
