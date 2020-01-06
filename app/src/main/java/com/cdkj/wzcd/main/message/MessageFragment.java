package com.cdkj.wzcd.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.main.MainMessageFragment;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.message.adapter.MessageAdapter;
import com.cdkj.wzcd.main.message.bean.MessageBean;
import retrofit2.Call;

import java.util.List;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:49
 */
public class MessageFragment extends AbsRefreshListFragment {

    private String departmentCode;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MessageFragment getInstance(String departmentCode) {
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, departmentCode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void afterCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        departmentCode = getArguments().getString(CdRouteHelper.DATA_SIGN);

        initRefreshHelper(10);
        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        MessageAdapter mAdapter = new MessageAdapter(listData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            MessageBean item = mAdapter.getItem(position);
            MessageActivity.open(mActivity, item);

        });
        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, Object> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("status", "1");
        map.put("publishDepartmentCode", departmentCode);

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getMessagePage("632725", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<MessageBean>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<MessageBean> data, String SucMessage) {
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
}
