package com.cdkj.wzcd.main.credit.module.lrfk;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.interfaces.BaseRefreshCallBack;
import com.cdkj.baselibrary.interfaces.RefreshHelper;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActYhhtBinding;
import com.cdkj.wzcd.main.credit.module.lrfk.adapter.YhhtAdapter;
import com.cdkj.wzcd.main.credit.module.lrfk.bean.YhhtBean;
import com.cdkj.wzcd.main.credit.module.zrzl.CarBrandActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.CarBrandAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarBrandBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

import java.util.List;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2020/1/1 21:33
 */
public class YhhtActivity extends AbsBaseLoadActivity {

    private ActYhhtBinding mBinding;

    private RefreshHelper mRefreshHelper;

    private ZrzlBean bean;

    public static void open(Context context, ZrzlBean bean) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, YhhtActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_yhht, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("银行合同");

        init();
        initListener();
        initRefreshHelper();

    }

    private void init() {
        bean = (ZrzlBean) getIntent().getSerializableExtra(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {

        mBinding.btnConfirm.setOnClickListener(v -> {
            finish();
        });
    }

    protected void initRefreshHelper() {
        mRefreshHelper = new RefreshHelper(this, new BaseRefreshCallBack(this) {
            @Override
            public View getRefreshLayout() {
                mBinding.refreshLayout.setEnableLoadmore(false);
                return mBinding.refreshLayout;
            }

            @Override
            public RecyclerView getRecyclerView() {
                return mBinding.rv;
            }

            @Override
            public RecyclerView.Adapter getAdapter(List listData) {
                YhhtAdapter mAdapter = new YhhtAdapter(listData);
                return mAdapter;
            }

            @Override
            public void getListDataRequest(int pageIndex, int limit, boolean isShowDialog) {
                getListRequest(pageIndex, limit, isShowDialog);
            }
        });

        mRefreshHelper.init(20);
        mRefreshHelper.onDefaultMRefresh(true);
    }


    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {

        if (null == bean) {
            return;
        }

        if (null == bean.getCreditUser()) {
            return;
        }

        Map<String, Object> map = RetrofitUtils.getRequestMap();

        map.put("customerName", bean.getCreditUser().getUserName());
        map.put("idNo", bean.getCreditUser().getIdNo());
        map.put("limit", limit);
        map.put("start", pageIndex);

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getYhhtPage("632235", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<YhhtBean>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<YhhtBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无合同", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}

