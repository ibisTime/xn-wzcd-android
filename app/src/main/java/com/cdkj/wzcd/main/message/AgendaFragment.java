package com.cdkj.wzcd.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.credit.module.djcz.DjczActivity;
import com.cdkj.wzcd.main.credit.module.dzhl.DzhlActivity;
import com.cdkj.wzcd.main.credit.module.fsdy.FsdyActivity;
import com.cdkj.wzcd.main.credit.module.ljdj.LjdjActivity;
import com.cdkj.wzcd.main.credit.module.lrfk.LrfkActivity;
import com.cdkj.wzcd.main.credit.module.qrdy.QrdyActivity;
import com.cdkj.wzcd.main.credit.module.qrsk.QrskActivity;
import com.cdkj.wzcd.main.credit.module.rd.RdActivity;
import com.cdkj.wzcd.main.credit.module.yhsj.YhsjActivity;
import com.cdkj.wzcd.main.credit.module.yhtj.YhtjActivity;
import com.cdkj.wzcd.main.credit.module.yksh.YkshActivity;
import com.cdkj.wzcd.main.credit.module.yksq.YksqActivity;
import com.cdkj.wzcd.main.credit.module.zdhl.ZdhlActivity;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.message.adapter.AgendaAdapter;
import com.cdkj.wzcd.main.message.adapter.MessageAdapter;
import com.cdkj.wzcd.main.message.bean.AgendaBean;
import retrofit2.Call;

import java.util.List;
import java.util.Map;


/**
 * @author : qianLei
 * @since : 2019/12/25 14:49
 */
public class AgendaFragment extends AbsRefreshListFragment {

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static AgendaFragment getInstance() {
        AgendaFragment fragment = new AgendaFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void afterCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        initRefreshHelper(10);
        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        AgendaAdapter mAdapter = new AgendaAdapter(listData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            AgendaBean item = mAdapter.getItem(position);
            onItemClick(item);
        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, Object> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("status", "0");
        map.put("userId", SPUtilHelper.getUserId());

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getAgendaPage("632525", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<AgendaBean>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<AgendaBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无消息", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void onItemClick(AgendaBean item) {

        switch (item.getRefNode()) {

            case "a1":
            case "a1x":
                ZrzlActivity.open(mActivity, item.getBizCode());
                break;

            case "a2":
                ZrshActivity.open(mActivity, item.getBizCode());
                break;

            case "b1":
                YksqActivity.open(mActivity, item.getBizCode());
                break;

            case "b3":
                YkshActivity.open(mActivity, item.getBizCode());
                break;

            case "b4":
                ZdhlActivity.open(mActivity, item.getBizCode());
                break;

            case "b5":
                DzhlActivity.open(mActivity, item.getBizCode());
                break;

            case "c1":
                LjdjActivity.open(mActivity, item.getBizCode());
                break;

            case "c2":
                DjczActivity.open(mActivity, item.getBizCode());
                break;

            case "d1":
                YhsjActivity.open(mActivity, item.getBizCode());
                break;

            case "d2":
                YhtjActivity.open(mActivity, item.getBizCode());
                break;

            case "d3":
                LrfkActivity.open(mActivity, item.getBizCode());
                break;

            case "d4":
                QrskActivity.open(mActivity, item.getBizCode());
                break;

            case "e1":
                FsdyActivity.open(mActivity, item.getBizCode());
                break;

            case "e2":
                QrdyActivity.open(mActivity, item.getBizCode());
                break;

            case "f1":
                RdActivity.open(mActivity, item.getBizCode());
                break;


        }

    }
}
