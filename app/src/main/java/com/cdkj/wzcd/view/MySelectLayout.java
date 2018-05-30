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

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMySelectHorizontalBinding;
import com.cdkj.wzcd.model.MySelectModel;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import java.util.List;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MySelectLayout extends LinearLayout {

    private Context context;
    private LayoutMySelectHorizontalBinding mBinding;

    private List<MySelectModel> mData;
    private MySelectInterface mySelectInterface;

    private String txtTitle;
    private String txtContent;

    private String[] mKeyList;

    private String mValue;
    private String[] mValueList;

    // 初始默认不选中
    private int selectIndex = -1;

    public MySelectLayout(Context context) {
        this(context, null);
    }

    public MySelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySelectLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MySelectLayout_txt_select_title);
        txtContent = typedArray.getString(R.styleable.MySelectLayout_txt_select_content);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        if (!TextUtils.isEmpty(txtContent))
            mBinding.tvContent.setText(txtContent);
    }


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_select_horizontal, this, true);

        initListener();
    }

    private void initListener() {
        mBinding.llRoot.setOnClickListener(view -> {
            if (mData == null)
                return;
            select();
        });
    }

    public void setData(List<MySelectModel> data, MySelectInterface selectInterface){
        mData = data;
        mySelectInterface = selectInterface;
    }

    private void select() {
        mKeyList = new String[mData.size()];
        mValueList = new String[mData.size()];


        int index = 0;
        for (MySelectModel model : mData) {
            mKeyList[index] = model.getKey();
            mValueList[index] = model.getValue();
            index++;
        }

        if (index == 0)
            return;

        new AlertDialog.Builder(context).setTitle("请选择").setSingleChoiceItems(
                mKeyList, selectIndex, (dialog, which) -> {

                    selectIndex = which;
                    mValue = mValueList[which];
                    mBinding.tvContent.setText(mKeyList[which]);

                    mySelectInterface.onClick(dialog,which);

                    dialog.dismiss();
                }).setNegativeButton("取消", null).show();
    }

    public String getDataValue(){
        return mValue;
    }
}
