package com.cdkj.wzcd.main.credit.module.zrzl.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.FrgDzxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.CzrzAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarModelBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CzrzBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailCzrzFragment extends AbsRefreshListFragment {

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailCzrzFragment getInstance() {
        DetailCzrzFragment fragment = new DetailCzrzFragment();
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void afterCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        initRefreshHelper(20);
        mRefreshHelper.onDefaultMRefresh(true);

    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        CzrzAdapter mAdapter = new CzrzAdapter(listData);
        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {

        Map<String, String> map = new HashMap<>();
        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("code", ((CreditActivity) mActivity).code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCzrzPage("623535", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CzrzBean>>(mActivity) {

            @Override
            protected void onSuccess(ResponseInListModel<CzrzBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无数据", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


}
