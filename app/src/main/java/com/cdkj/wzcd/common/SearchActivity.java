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
import android.widget.CheckBox;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.interfaces.BaseRefreshCallBack;
import com.cdkj.baselibrary.interfaces.RefreshHelper;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActCarBrandBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;

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
    private boolean isSelectMultiple;
    ArrayList<DataDictionary> selectDataList = new ArrayList<>();

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

    /**
     * @param context          上下文
     * @param topHintText      提示搜索语
     * @param tag              标记  可当做id
     * @param isSelectMultiple 是否是多选
     * @param data             数据集合
     */
    public static void open(Context context, String topHintText, String tag, boolean isSelectMultiple, ArrayList<DataDictionary> data) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("topHintText", topHintText);
        intent.putExtra("tag", tag);
        intent.putExtra("isSelectMultiple", isSelectMultiple);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_car_brand, null, false);
        if (getIntent() != null) {
            itemShowImg = getIntent().getBooleanExtra("itemShowImg", false);
            topHintText = getIntent().getStringExtra("topHintText");
            isSelectMultiple = getIntent().getBooleanExtra("isSelectMultiple", false);
            tag = getIntent().getStringExtra("tag");
            data = (ArrayList<DataDictionary>) getIntent().getSerializableExtra("data");
        }
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setShowTitle(false);//取消标题
        if (isSelectMultiple) {
            mBinding.tvSearch.setText("确定");
        }
        mBinding.etSearch.setHint(topHintText);
        initRefreshHelper();
        mRefreshHelper.setData(data, "暂无数据", 0);
        initListener();
    }

    private void initListener() {
        mBinding.tvSearch.setOnClickListener(v -> {
            if (isSelectMultiple) {
                if (selectDataList.size() <= 0) {
                    ToastUtil.show(this, "没有选择数据");
                    finish();
                    return;
                }
                //单选
                EventBus.getDefault().post(new EventBean().setTag(tag).setValue(selectDataList));
                finish();
                return;
            }
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
                SearchAdapter mAdapter = new SearchAdapter(listData, false, true, isSelectMultiple);
                mAdapter.setOnItemClickListener((adapter, view, position) -> {

                    DataDictionary item = mAdapter.getItem(position);

                    if (isSelectMultiple) {
                        //多选
//                        selectDataList.add(item);
                    } else {
                        //单选
                        EventBus.getDefault().post(new EventBean().setTag(tag).setValue1(item.getDkey())
                                .setValue2(item.getDvalue()).setValue3(data.indexOf(item) + ""));
                        finish();
                    }
                });

                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.cb_check:
                                if (((CheckBox) view).isChecked()) {
                                    selectDataList.add((DataDictionary) adapter.getItem(position));
                                } else {
                                    selectDataList.remove(adapter.getItem(position));
                                }
                                break;
                        }
                    }
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
