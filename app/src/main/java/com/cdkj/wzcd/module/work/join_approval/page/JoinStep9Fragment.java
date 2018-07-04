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
import com.cdkj.wzcd.databinding.FragmentJoinStep9Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep9Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep9Binding mBinding;

    private NodeListModel data;

    public static JoinStep9Fragment getInstance(String code) {
        JoinStep9Fragment fragment = new JoinStep9Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step9, null, false);

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
        mBinding.myMlSecondHgz.build(mActivity, 10, 0);
        mBinding.myMlSecondOdometer.build(mActivity, 10, 1);
        mBinding.myMlSecondCarFrontPic.build(mActivity, 10, 2);
        mBinding.myMlSecondConsolePic.build(mActivity, 10, 3);
        mBinding.myMlSecond300Pdf.build(mActivity, 10, 4);
        mBinding.myMlSecondQxbPic.build(mActivity, 10, 5);
        mBinding.myMlSecondCarInPic.build(mActivity, 10, 6);
        mBinding.myMlSecondNumber.build(mActivity, 10, 7);
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

                if (requestCode == mBinding.myMlSecondHgz.getRequestCode()){
                    mBinding.myMlSecondHgz.addList(key);
                }

                if (requestCode == mBinding.myMlSecondOdometer.getRequestCode()){
                    mBinding.myMlSecondOdometer.addList(key);
                }

                if (requestCode == mBinding.myMlSecondCarFrontPic.getRequestCode()){
                    mBinding.myMlSecondCarFrontPic.addList(key);
                }

                if (requestCode == mBinding.myMlSecondConsolePic.getRequestCode()){
                    mBinding.myMlSecondConsolePic.addList(key);
                }

                if (requestCode == mBinding.myMlSecond300Pdf.getRequestCode()){
                    mBinding.myMlSecond300Pdf.addList(key);
                }

                if (requestCode == mBinding.myMlSecondQxbPic.getRequestCode()){
                    mBinding.myMlSecondQxbPic.addList(key);
                }

                if (requestCode == mBinding.myMlSecondCarInPic.getRequestCode()){
                    mBinding.myMlSecondCarInPic.addList(key);
                }

                if (requestCode == mBinding.myMlSecondNumber.getRequestCode()){
                    mBinding.myMlSecondNumber.addList(key);
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

        map.put("secondHgz",mBinding.myMlSecondHgz.getListData());
        map.put("secondOdometer",mBinding.myMlSecondOdometer.getListData());
        map.put("secondCarFrontPic",mBinding.myMlSecondCarFrontPic.getListData());
        map.put("secondConsolePic",mBinding.myMlSecondConsolePic.getListData());
        map.put("second300Pdf",mBinding.myMlSecond300Pdf.getListData());
        map.put("secondQxbPic",mBinding.myMlSecondQxbPic.getListData());
        map.put("secondCarInPic",mBinding.myMlSecondCarInPic.getListData());
        map.put("secondNumber",mBinding.myMlSecondNumber.getListData());

        return map;
    }
}
