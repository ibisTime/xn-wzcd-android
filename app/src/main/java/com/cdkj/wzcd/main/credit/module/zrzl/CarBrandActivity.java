package com.cdkj.wzcd.main.credit.module.zrzl;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.interfaces.BaseRefreshCallBack;
import com.cdkj.baselibrary.interfaces.RefreshHelper;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActCarBrandBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.CarBrandAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarBrandBean;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

import java.util.List;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/28 16:45
 */
public class CarBrandActivity extends AbsBaseLoadActivity {

    private ActCarBrandBinding mBinding;

    private String name = "";
    private RefreshHelper mRefreshHelper;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CarBrandActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_car_brand, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setShowTitle(false);//取消标题

        initListener();
        initRefreshHelper();
    }

    private void initListener() {
        mBinding.tvSearch.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mBinding.etSearch.getText().toString())) {
                ToastUtil.show(this, "请输入品牌");
                return;
            }

            name = mBinding.etSearch.getText().toString();
            mRefreshHelper.onDefaultMRefresh(true);

        });

        mBinding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * 初始化刷新相关
     */
    protected void initRefreshHelper() {
        mRefreshHelper = new RefreshHelper(this, new BaseRefreshCallBack(this) {
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
                CarBrandAdapter mAdapter = new CarBrandAdapter(listData);
                mAdapter.setOnItemClickListener((adapter, view, position) -> {

                    CarBrandBean item = mAdapter.getItem(position);

                    EventBus.getDefault()
                            .post(new EventBean().setTag("jbxx_brand").setValue1(item.getCode())
                                    .setValue2(item.getName()));
                    finish();
                });
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
        Map<String, Object> map = RetrofitUtils.getRequestMap();

        map.put("name", name);
        map.put("type", "1");
        map.put("status", "1");

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCarBrandList("630406", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseListCallBack<CarBrandBean>(this) {
            @Override
            protected void onSuccess(List<CarBrandBean> data, String SucMessage) {
                mRefreshHelper.setData(data, "暂无品牌", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
