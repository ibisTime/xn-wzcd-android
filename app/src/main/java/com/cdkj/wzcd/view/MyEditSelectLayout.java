package com.cdkj.wzcd.view;

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
import com.cdkj.wzcd.databinding.LayoutMyEditSelectBinding;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 可编辑的下拉框Layout
 * Created by cdkj on 2018/5/29.
 */

public class MyEditSelectLayout extends LinearLayout {

    private Context context;
    private LayoutMyEditSelectBinding mBinding;

    private List<DataDictionary> mData;
    private MySelectInterface mySelectInterface;

    private String txtTitle;
    private String txtContent;

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

    public MyEditSelectLayout(Context context) {
        this(context, null);
    }

    public MyEditSelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditSelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditSelectLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyEditSelectLayout_txt_edit_select_title);
        txtContent = typedArray.getString(R.styleable.MyEditSelectLayout_txt_edit_select_content);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_edit_select, this, true);

        initListener();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        if (!TextUtils.isEmpty(txtContent))
            mBinding.edtInput.setText(txtContent);
    }

    public void setText(String text) {

        mBinding.edtInput.setText(text);

        selectIndex = -2;

    }

    public String getText() {
        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            return "";
        }
        return mBinding.edtInput.getText().toString().trim();

    }


    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不应相应点击事件
     *
     * @param text
     */
    public void setTextByRequest(String text) {
        // 隐藏更多
        mBinding.llSelect.setVisibility(GONE);
        // 设置不可弹出下拉
        isOnClickEnable = false;
        // 设置内容
        mBinding.edtInput.setText(text);

        selectIndex = -2;
    }

    /**
     * 设置不可下拉
     *
     * @param onClickEnable
     */
    public void setOnDropEnable(boolean onClickEnable) {
        isOnClickEnable = onClickEnable;
    }

    /**
     * 根据key自动获取对应value填入
     *
     * @param key
     */
    public void setContentByKey(String key) {

        if (TextUtils.isEmpty(key)) {
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

        mBinding.edtInput.setText(mValue);
    }


    /**
     * 设置"是否／01"类型数据
     *
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param selectInterface
     */
    public void setData(String key1, String value1, String key2, String value2, MySelectInterface selectInterface) {

        if (TextUtils.isEmpty(key1) || TextUtils.isEmpty(value1) || TextUtils.isEmpty(key2) || TextUtils.isEmpty(value2))
            return;

        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey(key1).setDvalue(value1));
        data.add(new DataDictionary().setDkey(key2).setDvalue(value2));

        mData = data;
        mySelectInterface = selectInterface;

    }

    /**
     * 设置0否，1是 类型数据
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
     * 直接设置List数据,但不可下拉
     *
     * @param data
     */
    public void setDataNoDrop(List<DataDictionary> data) {
        // 隐藏更多
        mBinding.llSelect.setVisibility(GONE);
        // 设置不可弹出下拉
        isOnClickEnable = false;
        setData(data, null);

    }


    private void initListener() {
        mBinding.llRoot.setOnClickListener(view -> {

            if (!isOnClickEnable)
                return;

            if (mData == null || mData.size() == 0) {
                ToastUtil.show(getContext(), "没有可选列表");
                return;
            }
            showDrop();

        });
    }

    /**
     * 初始下拉数据mKeyList和mValueList
     *
     * @return 数据Size
     */
    private int initList() {
        mKeyList = new String[mData.size()];
        mValueList = new String[mData.size()];

        int size = 0;
        for (DataDictionary model : mData) {
            mKeyList[size] = model.getDkey();
            mValueList[size] = model.getDvalue();
            size++;
        }
        return size;
    }

    /**
     * 显示下拉框
     */
    private void showDrop() {
        int size = initList();

        if (size == 0)
            return;

        new AlertDialog.Builder(context).setTitle("请选择").setSingleChoiceItems(
                mValueList, selectIndex, (dialog, which) -> {

                    selectIndex = which;

                    mKey = mKeyList[which];
                    mValue = mValueList[which];

                    mBinding.edtInput.setText(mValue);

                    if (mySelectInterface != null)
                        mySelectInterface.onClick(dialog, which);

                    dialog.dismiss();
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 检查下拉框选择值
     *
     * @return
     */
    public boolean check() {
        if (selectIndex == -1) {
            ToastUtil.show(context, "请选择" + mBinding.tvTitle.getText());
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

}
