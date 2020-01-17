package com.cdkj.wzcd.common;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.interfaces.BaseRefreshCallBack;
import com.cdkj.baselibrary.interfaces.RefreshHelper;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActCarBrandBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @updateDts 2020/1/7
 */
public class SearchActivity extends AbsBaseLoadActivity {

    private ActCarBrandBinding mBinding;
    private String name;
    private RefreshHelper mRefreshHelper;
    //    private boolean enableRefresh;
    private boolean itemShowImg;
    private String topHintText = "请输入搜索内容";
    private ArrayList<DataDictionary> data;
    private String tag;
    private static Object clazz;
//
//    /**
//     * @param context       上下文
//     * @param apiCode       接口
//     * @param map           接口参数
//     * @param clazz         接口参数返回的实例类型
//     * @param enableRefresh 是否开启刷新和加载
//     * @param itemShowImg   条目是否显示logo
//     * @param <T>
//     */
//    public static <T> void open(Context context, String apiCode, HashMap map, T clazz, boolean enableRefresh, boolean itemShowImg) {
//        if (context == null) {
//            return;
//        }
//        SearchActivity.clazz = clazz;
//        Intent intent = new Intent(context, SearchActivity.class);
//        intent.putExtra("enableRefresh", enableRefresh);
//        intent.putExtra("itemShowImg", itemShowImg);
//        intent.putExtra("map", map);
//        intent.putExtra("apiCode", apiCode);
//        context.startActivity(intent);
//    }

    public static void open(Context context, String topHintText, String tag, ArrayList<DataDictionary> data) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("topHintText", topHintText);
        intent.putExtra("tag", tag);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

//    public static SearchDataAddListener dataListener;

//    /**
//     * 如果需要分页加载的话 通过这个回调设置数据
//     *
//     * @param dataListener
//     */
//    public static void setSearchDataAddListener(SearchDataAddListener dataListener) {
//        SearchActivity.dataListener = dataListener;
//    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_car_brand, null, false);
        if (getIntent() != null) {
//            enableRefresh = getIntent().getBooleanExtra("enableRefresh", false);
            itemShowImg = getIntent().getBooleanExtra("itemShowImg", false);
            topHintText = getIntent().getStringExtra("topHintText");
            tag = getIntent().getStringExtra("tag");
            data = (ArrayList<DataDictionary>) getIntent().getSerializableExtra("data");
        }
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setShowTitle(false);//取消标题
        mBinding.etSearch.setHint(topHintText);

        initRefreshHelper();
        mRefreshHelper.setData(data, "暂无数据", 0);
        initListener();
//        if (enableRefresh) {
//        } else {
//            mRefreshHelper.setData(data, "暂无数据", 0);
//
//        }
    }

    private void initListener() {
        mBinding.tvSearch.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mBinding.etSearch.getText().toString())) {
                ToastUtil.show(this, topHintText);
                return;
            }
            name = mBinding.etSearch.getText().toString();
            setSearchData();
        });

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(mBinding.etSearch.getText().toString())) {
                    mRefreshHelper.setData(data, "暂无数据", 0);
                } else {
                    name = mBinding.etSearch.getText().toString();
                    setSearchData();
                }

            }
        });

        mBinding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * 设置筛选内容
     */
    private void setSearchData() {
        ArrayList<DataDictionary> searchData = new ArrayList<>();
        //将搜索数据和列表数据的全部变成小写  再去判断  这样就实现了不区分大小写进行筛选了
        for (DataDictionary datum : data) {
            if (datum.getDvalue().toLowerCase().contains(name.toLowerCase())) {
                searchData.add(datum);
            }
        }
        mRefreshHelper.setData(searchData, "暂无数据", 0);
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
                SearchAdapter mAdapter = new SearchAdapter(listData, false, true);
                mAdapter.setOnItemClickListener((adapter, view, position) -> {
                    DataDictionary item = mAdapter.getItem(position);
                    finish();
                    EventBus.getDefault().post(new EventBean().setTag(tag).setValue1(item.getDkey())
                            .setValue2(item.getDvalue()).setValue3(data.indexOf(item) + ""));
                });
                return mAdapter;
            }

            @Override
            public void getListDataRequest(int pageIndex, int limit, boolean isShowDialog) {
//                if (dataListener != null) {
//                    List listData = dataListener.getListData(pageIndex, limit, isShowDialog);
//                    mRefreshHelper.setData(listData, "暂无数据", 0);
//                }
            }
        });
        mRefreshHelper.init(20);
        mBinding.refreshLayout.setEnableLoadmore(false);
        mBinding.refreshLayout.setEnableRefresh(false);
        mRefreshHelper.setData(data, "暂无数据", 0);

//        if (enableRefresh) {
//            mRefreshHelper.init(20);
//            mRefreshHelper.onDefaultMRefresh(true);
//        } else {
//            mRefreshHelper.init(20);
//            mBinding.refreshLayout.setEnableLoadmore(false);
//            mBinding.refreshLayout.setEnableRefresh(false);
//            mRefreshHelper.setData(data, "暂无数据", 0);
//        }
    }
}

//interface SearchDataAddListener {
//
//    List getListData(int pageIndex, int limit, boolean isShowDialog);
//}