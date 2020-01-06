package com.cdkj.wzcd.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutBaseSelectBinding;
import com.cdkj.wzcd.databinding.LayoutMySelectHorizontalBinding;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.view.interfaces.MyFrontSelectInterface;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import java.util.*;

import static com.cdkj.wzcd.util.DataDictionaryHelper.send_type;

/**
 * 下拉框Layout
 * Created by cdkj on 2018/5/29.
 */

public class BaseSelectLayout extends LinearLayout {

    private Context context;
    private LayoutBaseSelectBinding mBinding;

    private List<DataDictionary> mData;
    private MySelectInterface mySelectInterface;
    private MyFrontSelectInterface myFrontSelectInterface;

    private String field;
    private String txtTitle;
    private String txtContent;
    private boolean isRequired;

    //
    private String mKey;
    private String[] mKeyList;

    private String mValue;
    private String[] mValueList;

    // 初始默认不选中
    private int selectIndex = -1; // -1:下拉数据已初始化，但还未选择 ，-2:直接通过请求数据添加Layout内容，不需要初始化下拉数据，也不需要选择

    private boolean isOnClickEnable = true;

//    protected LoadingDialog loadingDialog;
//
//    private Call call;

    public BaseSelectLayout(Context context) {
        this(context, null);
    }

    public BaseSelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseSelectLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseSelectLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseSelectLayout_title);
        txtContent = typedArray.getString(R.styleable.BaseSelectLayout_text);
        isRequired = typedArray.getBoolean(R.styleable.BaseSelectLayout_isRequired, false);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_select, this,
                        true);

        initListener();
    }

    private void setData() {
        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvContent.setHint("请选择" + txtTitle);

        if (!TextUtils.isEmpty(txtContent)) {
            mBinding.tvContent.setText(txtContent);
        }
    }

    public void setText(String text) {

        mBinding.tvContent.setText(text);

        selectIndex = -2;

    }

    /**
     * 直接设置key和value，用于mKeyList为空或其size=0时，也就是未初始化Layout但需要添加数据时
     *
     * @param key
     * @param value
     */
    public void setTextAndKey(String key, String value) {

        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }

        mKey = key;
        mValue = value;

        mBinding.tvContent.setText(mValue);

        selectIndex = -2;

    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不应相应点击事件
     *
     * @param text
     */
    public void setTextByRequest(String text) {
        // 隐藏更多
        mBinding.ivMore.setVisibility(GONE);
        // 设置不可弹出下拉
        isOnClickEnable = false;
        // 设置内容
        mBinding.tvContent.setText(text);

        selectIndex = -2;
    }

    public void setTextByRequestByKey(String key) {
        // 隐藏更多
        mBinding.ivMore.setVisibility(GONE);
        // 设置不可弹出下拉
        isOnClickEnable = false;

        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (mData == null) {
            return;
        }
        int i = 0;

        for (DataDictionary data : mData) {
            if (TextUtils.equals(data.getDkey(), key)) {
                mKey = data.getDkey();
                mValue = data.getDvalue();
                selectIndex = i;
                break;
            }
            i++;
        }

        mBinding.tvContent.setText(mValue);

        selectIndex = -2;
    }

    public void setOnClickEnable(boolean onClickEnable) {
        isOnClickEnable = onClickEnable;
        mBinding.ivMore.setVisibility(onClickEnable ? VISIBLE : GONE);
        mBinding.tvContent.setHint("");

    }

    public void setContentByKey(String key) {

        if (TextUtils.isEmpty(key) || key == null) {
            return;
        }

        if (mData == null || mData.size() == 0) {
            return;
        }
        int i = 0;

        for (DataDictionary data : mData) {
            if (TextUtils.equals(data.getDkey(), key)) {
                mKey = data.getDkey();
                mValue = data.getDvalue();
                selectIndex = i;
                break;
            }
            i++;
        }

        mBinding.tvContent.setText(mValue);
    }

    /**
     * 设置"是否／01"相同类型数据
     *
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     */
    public void setData(String key1, String value1, String key2, String value2) {
        setData(key1, value1, key2, value2, null);
    }

    /**
     * 设置"是否／01"相同类型数据
     *
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param selectInterface
     */
    public void setData(String key1, String value1, String key2, String value2,
            MySelectInterface selectInterface) {

        if (TextUtils.isEmpty(key1) || TextUtils.isEmpty(value1) || TextUtils.isEmpty(key2)
                || TextUtils.isEmpty(value2)) {
            return;
        }

        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey(key1).setDvalue(value1));
        data.add(new DataDictionary().setDkey(key2).setDvalue(value2));

        mData = data;
        mySelectInterface = selectInterface;

    }

    /**
     * 设置0否，1是 数据
     *
     * @param selectInterface
     */
    public void setDataIs(MySelectInterface selectInterface) {

        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey("1").setDvalue("是"));
        data.add(new DataDictionary().setDkey("0").setDvalue("否"));

        mData = data;
        mySelectInterface = selectInterface;

    }


    /**
     * 根据parentKey直接设置List数据
     *
     * @param parentKey
     */
    public void setData(String parentKey) {
        setData(DataDictionaryHelper.getListByParentKey(parentKey), null);
    }

    /**
     * 根据parentKey直接设置List数据
     *
     * @param parentKey
     */
    public void setData(String parentKey, MySelectInterface selectInterface) {
        setData(DataDictionaryHelper.getListByParentKey(parentKey), selectInterface);
    }

    /**
     * 直接设置List数据
     *
     * @param data
     */
    public void setData(List<DataDictionary> data) {
        mData = data;
    }

    /**
     * 直接设置List数据
     *
     * @param data
     * @param selectInterface
     */
    public void setData(List<DataDictionary> data, MySelectInterface selectInterface) {
        mData = data;
        mySelectInterface = selectInterface;

    }

    /**
     * 直接设置List数据  弹窗之前会有一个回调可做判断.返回true 则不在弹窗
     *
     * @param data
     * @param selectInterface
     */
    public void setData(List<DataDictionary> data, MyFrontSelectInterface frontSelectInterface,
            MySelectInterface selectInterface) {
        mData = data;
        mySelectInterface = selectInterface;
        myFrontSelectInterface = frontSelectInterface;

    }

//    public void setData(List<DataDictionary> data) {
//        // 隐藏更多
//        mBinding.ivMore.setVisibility(GONE);
//        // 设置不可弹出下拉
//        isOnClickEnable = false;
//        setData(data, null);
//
//    }


    private void initListener() {
        mBinding.llRoot.setOnClickListener(view -> {

            if (!isOnClickEnable) {
                return;
            }

            if (mData == null || mData.size() == 0) {
                ToastUtil.show(getContext(), "没有可选列表");
                return;
            }
            showSelect();

        });
    }

    public void setListener(OnClickListener listener) {
        mBinding.llRoot.setOnClickListener(listener);
    }


    public void showSelect() {
        int index = initList();

        if (index == 0) {
            return;
        }

        if (myFrontSelectInterface != null) {
            if (myFrontSelectInterface.onClick()) {
                //返回True  就不在继续弹窗了
                return;
            }
        }

        new AlertDialog.Builder(context).setTitle("请选择").setSingleChoiceItems(
                mValueList, selectIndex, (dialog, which) -> {

                    selectIndex = which;

                    mKey = mKeyList[which];
                    mValue = mValueList[which];

                    mBinding.tvContent.setText(mValue);

                    if (mySelectInterface != null) {
                        mySelectInterface.onClick(dialog, which);
                    }

                    dialog.dismiss();
                }).setNegativeButton("取消", null).show();
    }

    private int initList() {
        mKeyList = new String[mData.size()];
        mValueList = new String[mData.size()];

        int index = 0;
        for (DataDictionary model : mData) {
            mKeyList[index] = model.getDkey();
            mValueList[index] = model.getDvalue();
            index++;
        }
        return index;
    }

    public boolean check() {
        if (!isRequired) {
            return false;
        }

        if (selectIndex == -1) {
            ToastUtil.show(context, "请选择" + mBinding.tvTitle.getText());
            return true;
        }

        return false;
    }

    public boolean checkNoTip() {
        if (selectIndex == -1) {
            return true;
        }

        return false;
    }

    public String getDataKey() {
        return mKey;
    }

    public String getDataValue() {

        if (selectIndex == -1) {
            ToastUtil.show(context, "请选择" + mBinding.tvTitle.getText());
            return null;
        }

        return mValue;
    }

    public String getTitle() {
        return mBinding.tvTitle.getText().toString().trim();
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();

        if (!TextUtils.isEmpty(field)) {
            map.put(field, mKey);
        }

        return map;
    }

    public String getField() {
        return field;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        clearCall();
    }

}
