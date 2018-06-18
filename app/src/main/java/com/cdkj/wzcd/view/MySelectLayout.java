package com.cdkj.wzcd.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.dialog.LoadingDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.LayoutMySelectHorizontalBinding;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MySelectLayout extends LinearLayout {

    public static int DATA_DICTIONARY = 0;
    public static int SYSTEM_PARAMETER = 1;

    private Context context;
    private LayoutMySelectHorizontalBinding mBinding;

    private Activity mActivity;
    private boolean mIsRequest;
    private int mRequestType;
    private String mParentKey;

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

    protected LoadingDialog loadingDialog;

    private Call call;

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

    public void setText(String text) {

        mBinding.tvContent.setText(text);

        selectIndex = -2;

    }


    public void setTextAndKey(String key, String value) {

        mKey = key;
        mValue = value;

        mBinding.tvContent.setText(mValue);

        selectIndex = -2;

    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不应相应点击时间
     * @param text
     */
    public void setTextByRequest(String text){
        // 隐藏更多
        mBinding.ivMore.setVisibility(GONE);
        // 设置不可弹出下拉
        isOnClickEnable = false;
        // 设置内容
        mBinding.tvContent.setText(text);

        selectIndex = -2;
    }

    public void setOnClickEnable(boolean onClickEnable){
        isOnClickEnable = onClickEnable;
    }

    public void setContentByKey(String key){

        int i = 0;

        for(String str : mKeyList){
            if (TextUtils.equals(str, key)){
                selectIndex = i;
            }
            i++;
        }

        mBinding.tvContent.setText(mValueList[selectIndex]);
    }

    /**
     * 设置通过请求获取数据
     * @param activity 上下文
     * @param requestType 请求类型（数据字典，系统参数）
     * @param parentKey 请求数据key
     * @param selectInterface 下拉框选择回调
     */
    public void setData(Activity activity, int requestType, String parentKey, MySelectInterface selectInterface){
        mActivity = activity;
        mParentKey = parentKey;
        mRequestType = requestType;

        mySelectInterface = selectInterface;

        mIsRequest = true;
    }

    public void setData(List<DataDictionary> data, MySelectInterface selectInterface){
        mData = data;
        mySelectInterface = selectInterface;

        mIsRequest = false;
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_select_horizontal, this, true);

        initListener();
    }

    private void initListener() {
        mBinding.llRoot.setOnClickListener(view -> {

            if (!isOnClickEnable)
                return;

            if (mIsRequest) {
                if (mActivity == null)
                    return;

                getRequest();
            }else {
                if (mData == null)
                return;

                showSelect();
            }

        });
    }


    public void getRequest() {

        Map<String, String> map = new HashMap<>();
        map.put("dkey", "");
        map.put("orderColumn", "");
        map.put("orderDir", "");
        map.put("parentKey", mParentKey);
        map.put("type", "");
        map.put("updater", "");

        call = RetrofitUtils.createApi(MyApiServer.class).getDataDictionary("630036", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<DataDictionary>(mActivity) {

            @Override
            protected void onSuccess(List<DataDictionary> data, String SucMessage) {
                if (data== null)
                    return;

                mData = data;
                showSelect();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void showSelect() {
        mKeyList = new String[mData.size()];
        mValueList = new String[mData.size()];


        int index = 0;
        for (DataDictionary model : mData) {
            mKeyList[index] = model.getDkey();
            mValueList[index] = model.getDvalue();
            index++;
        }

        if (index == 0)
            return;

        new AlertDialog.Builder(context).setTitle("请选择").setSingleChoiceItems(
                mValueList, selectIndex, (dialog, which) -> {

                    selectIndex = which;

                    mKey = mKeyList[which];
                    mValue = mValueList[which];

                    mBinding.tvContent.setText(mValue);

                    if (mySelectInterface != null)
                        mySelectInterface.onClick(dialog,which);

                    dialog.dismiss();
                }).setNegativeButton("取消", null).show();
    }

    public boolean check(){
        if (selectIndex == -1){
            ToastUtil.show(context, "请选择"+mBinding.tvTitle.getText());
            return true;
        }

        return false;
    }

    public String getDataKey(){
        return mKey;
    }

    public String getDataValue(){

        if (selectIndex == -1){
            ToastUtil.show(context, "请选择"+mBinding.tvTitle.getText());
            return null;
        }

        return mValue;
    }

    public String getDataId(){

        if (selectIndex == -1){
            ToastUtil.show(context, "请选择"+mBinding.tvTitle.getText());
            return null;
        }

        return mData.get(selectIndex).getId();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearCall();
    }

    /**
     * 隐藏Dialog
     */
    public void disMissLoading() {
        if (null != loadingDialog) {
            loadingDialog.closeDialog();
        }
    }

    /**
     * 显示dialog
     */
    public void showLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(mActivity);
        }
        if (null != loadingDialog) {
            loadingDialog.showDialog();
        }
    }

    private void clearCall(){
        if (call != null)
            call.cancel();
    }

}
