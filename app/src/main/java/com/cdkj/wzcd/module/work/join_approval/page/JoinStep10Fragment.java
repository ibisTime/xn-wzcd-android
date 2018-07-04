package com.cdkj.wzcd.module.work.join_approval.page;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep10Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep10Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep10Binding mBinding;

    private NodeListModel data;

    public static JoinStep10Fragment getInstance(String code) {
        JoinStep10Fragment fragment = new JoinStep10Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step10, null, false);

        if (getArguments() != null) {
            code = getArguments().getString(DATA_SIGN);
        }

        data = ((JoinApplyActivity) mActivity).mData;

        initView();
        setView();


        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initView() {
        mBinding.myElOtherFilePdf.build(mActivity, 10, 0);
    }

    private void setView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        String path = data.getStringExtra(CameraHelper.staticPath);
        showLoadingDialog();
        new QiNiuHelper(mActivity).uploadSinglePic(new QiNiuHelper.QiNiuCallBack() {
            @Override
            public void onSuccess(String key) {

                if (requestCode == mBinding.myElOtherFilePdf.getRequestCode()){
                    mBinding.myElOtherFilePdf.addList(key);
                }

                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    public Map<String, Object> getData(){

        Map<String, Object> map = new HashMap<>();

        map.put("otherFilePdf",mBinding.myElOtherFilePdf.getListData());
        map.put("otherApplyNote",mBinding.myElOtherApplyNote.getText());

        return map;
    }
}
