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
import com.cdkj.wzcd.databinding.FragmentJoinStep7Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep7Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep7Binding mBinding;

    private NodeListModel data;

    public static JoinStep7Fragment getInstance(String code) {
        JoinStep7Fragment fragment = new JoinStep7Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step7, null, false);

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
        mBinding.myMlHousePic.build(mActivity, 10, 0);
        mBinding.myMlHouseUnitPic.build(mActivity, 10, 1);
        mBinding.myMlHouseDoorPic.build(mActivity, 10, 2);
        mBinding.myMlHouseRoomPic.build(mActivity, 10, 3);
        mBinding.myMlHouseCustomerPic.build(mActivity, 10, 4);
        mBinding.myMlHouseSaleCustomerPic.build(mActivity, 10, 5);
    }

    private void setView() {
        if (((JoinApplyActivity) mActivity).isDetails) {
            //小区外观
            mBinding.myMlHousePic.addListRequest(StringUtils.splitPIC(data.getHousePic()));
            //单元楼照片
            mBinding.myMlHouseUnitPic.addListRequest(StringUtils.splitPIC(data.getHouseUnitPic()));
            //门牌照片
            mBinding.myMlHouseDoorPic.addListRequest(StringUtils.splitPIC(data.getHouseDoorPic()));
            //客厅照片
            mBinding.myMlHouseRoomPic.addListRequest(StringUtils.splitPIC(data.getHouseRoomPic()));
            //主贷与住宅合影
            mBinding.myMlHouseCustomerPic.addListRequest(StringUtils.splitPIC(data.getHouseCustomerPic()));
            //签约员与客户合影
            mBinding.myMlHouseSaleCustomerPic.addListRequest(StringUtils.splitPIC(data.getHouseSaleCustomerPic()));
        } else {
            //小区外观
            mBinding.myMlHousePic.addList(StringUtils.splitPIC(data.getHousePic()));
            //单元楼照片
            mBinding.myMlHouseUnitPic.addList(StringUtils.splitPIC(data.getHouseUnitPic()));
            //门牌照片
            mBinding.myMlHouseDoorPic.addList(StringUtils.splitPIC(data.getHouseDoorPic()));
            //客厅照片
            mBinding.myMlHouseRoomPic.addList(StringUtils.splitPIC(data.getHouseRoomPic()));
            //主贷与住宅合影
            mBinding.myMlHouseCustomerPic.addList(StringUtils.splitPIC(data.getHouseCustomerPic()));
            //签约员与客户合影
            mBinding.myMlHouseSaleCustomerPic.addList(StringUtils.splitPIC(data.getHouseSaleCustomerPic()));
        }
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

                if (requestCode == mBinding.myMlHousePic.getRequestCode()) {
                    mBinding.myMlHousePic.addList(key);
                }

                if (requestCode == mBinding.myMlHouseUnitPic.getRequestCode()) {
                    mBinding.myMlHouseUnitPic.addList(key);
                }

                if (requestCode == mBinding.myMlHouseDoorPic.getRequestCode()) {
                    mBinding.myMlHouseDoorPic.addList(key);
                }

                if (requestCode == mBinding.myMlHouseRoomPic.getRequestCode()) {
                    mBinding.myMlHouseRoomPic.addList(key);
                }

                if (requestCode == mBinding.myMlHouseCustomerPic.getRequestCode()) {
                    mBinding.myMlHouseCustomerPic.addList(key);
                }

                if (requestCode == mBinding.myMlHouseSaleCustomerPic.getRequestCode()) {
                    mBinding.myMlHouseSaleCustomerPic.addList(key);
                }


                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<>();

        map.put("housePic", mBinding.myMlHousePic.getListData());
        map.put("houseUnitPic", mBinding.myMlHouseUnitPic.getListData());
        map.put("houseDoorPic", mBinding.myMlHouseDoorPic.getListData());
        map.put("houseRoomPic", mBinding.myMlHouseRoomPic.getListData());
        map.put("houseCustomerPic", mBinding.myMlHouseCustomerPic.getListData());
        map.put("houseSaleCustomerPic", mBinding.myMlHouseSaleCustomerPic.getListData());

        return map;
    }

}
