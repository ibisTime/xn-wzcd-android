package com.cdkj.wzcd.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.common.SearchActivity;
import com.cdkj.wzcd.custom.adapter.BaseMultipleSelectAdapter;
import com.cdkj.wzcd.custom.bean.BaseMultipleSelectBean;
import com.cdkj.wzcd.databinding.LayoutBaseSelectMultipleBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @updateDts 2020/2/4
 */
public class BaseMultipleSelectLayout extends LinearLayout {

    private String field;
    private String txtTitle;
    private boolean isRequired;
    private Context mContext;
    private LayoutBaseSelectMultipleBinding mBinding;
    private BaseMultipleSelectAdapter madapter;
    private ArrayList<DataDictionary> currentData;
    private ArrayList<DataDictionary> selectDataList;

    public BaseMultipleSelectLayout(Context context) {
        this(context, null);
    }

    public BaseMultipleSelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseMultipleSelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseMultipleSelectLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseMultipleSelectLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseMultipleSelectLayout_title);
        isRequired = typedArray.getBoolean(R.styleable.BaseMultipleSelectLayout_isRequired, false);
        typedArray.recycle();
        EventBus.getDefault().register(this);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_select_multiple, this,
                        true);

        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);
        mBinding.tvTitle.setText(txtTitle);

        selectDataList = new ArrayList<>();
        currentData = new ArrayList<>();
        madapter = new BaseMultipleSelectAdapter(true, currentData);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBinding.rv.setAdapter(madapter);
        madapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_close:
                adapter.remove(position);
//                    currentData.remove(position);
//                    madapter.notifyDataSetChanged();
                    break;
            }
        });
        initListener();
    }

    private void initListener() {
        mBinding.llRoot.setOnClickListener(v -> {

            SearchActivity.open(mContext, "请输入搜索内容", field, true, selectDataList);
        });
    }

    public void setSelectDataList(ArrayList<DataDictionary> data) {
        selectDataList.clear();
        selectDataList.addAll(data);
    }

    public void setDataList(ArrayList<DataDictionary> data) {
        currentData.clear();
        currentData.addAll(data);
        madapter.setEdit(true);
        madapter.notifyDataSetChanged();
    }

    public void setRequiredDataList(ArrayList<DataDictionary> data) {
        currentData.clear();
        currentData.addAll(data);
        madapter.setEdit(false);
        madapter.notifyDataSetChanged();

    }


    public boolean check() {
        if (!isRequired) {
            return false;
        }

        if (currentData.size() == 0) {
            ToastUtil.show(getContext(), "请选择" + txtTitle);
            return true;
        }

        return false;
    }

    public Map<String, String> getMap_String() {
        Map<String, String> map = new HashMap<>();
        String resultsStr = "";
        for (DataDictionary currentDatum : currentData) {
            resultsStr = resultsStr + ",";
        }
        if (resultsStr.length() > 0) {
            resultsStr = resultsStr.substring(0, resultsStr.length() - 1);
        }
        map.put(field, resultsStr);
        return map;
    }

    public Map<String, Object> getMap_List() {

        Map<String, Object> map = new HashMap<>();
        ArrayList<BaseMultipleSelectBean> results = new ArrayList<>();
        for (DataDictionary currentDatum : currentData) {
            results.add(new BaseMultipleSelectBean().setCode(currentDatum.getDkey()));
        }
        map.put(field, results);
        return map;
    }

    @Subscribe
    public void getEventData(EventBean bean) {
        if (TextUtils.equals(bean.getTag(), field)) {
            ArrayList<DataDictionary> valueData = (ArrayList<DataDictionary>) bean.getValue();
            currentData.addAll(valueData);
            madapter.notifyDataSetChanged();
        }
    }
}
