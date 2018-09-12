package com.cdkj.wzcd.module.work.join_approval.page;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep3Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep3Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep3Binding mBinding;

    private NodeListModel data;

    public static JoinStep3Fragment getInstance(String code) {
        JoinStep3Fragment fragment = new JoinStep3Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step3, null, false);

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
        mBinding.myMlHouseProperty.build(mActivity, 10, 0);
        mBinding.mySlIsHouseProperty.setData(getDataDictionaries(), (dialog, which) -> {
            mBinding.myMlHouseProperty.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });

        mBinding.myMlLicense.build(mActivity, 10, 1);
        mBinding.mySlIsLicense.setData(getDataDictionaries(), (dialog, which) -> {
            mBinding.myMlLicense.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });

        mBinding.mySlCarType.setData(getCarType(), null);

        mBinding.myMlDriceLicense.build(mActivity, 10, 2);
        mBinding.mySlIsDriceLicense.setData(getDataDictionaries(), (dialog, which) -> {
            mBinding.myMlDriceLicense.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });

        mBinding.myMlSiteProve.build(mActivity, 10, 3);
        mBinding.mySlIsSiteProve.setData(getDataDictionaries(), (dialog, which) -> {
            mBinding.myMlSiteProve.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });


    }

    private void setView() {

        if (((JoinApplyActivity) mActivity).isDetails) {
            //房产证
            mBinding.mySlIsHouseProperty.setTextByRequestByKey(data.getIsHouseProperty());
            if (!TextUtils.isEmpty(data.getIsHouseProperty())) {
                mBinding.myMlHouseProperty.setVisibility(TextUtils.equals(data.getIsHouseProperty(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlHouseProperty.addListRequest(StringUtils.splitPIC(data.getHouseProperty()));
            }

            //营业执照
            mBinding.mySlIsLicense.setTextByRequestByKey(data.getIsLicense());
            if (!TextUtils.isEmpty(data.getIsLicense())) {
                mBinding.myMlLicense.setVisibility(TextUtils.equals(data.getIsLicense(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlLicense.addListRequest(StringUtils.splitPIC(data.getLicense()));
            }

            //有无驾照  isDriceLicense
            mBinding.mySlIsDriceLicense.setTextByRequestByKey(data.getIsDriceLicense());

            if (!TextUtils.isEmpty(data.getIsDriceLicense())) {
                mBinding.myMlDriceLicense.setVisibility(TextUtils.equals(data.getIsDriceLicense(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlDriceLicense.addListRequest(StringUtils.splitPIC(data.getDriceLicense()));
            }

            mBinding.mySlIsSiteProve.setTextByRequestByKey(data.getIsSiteProve());
            if (!TextUtils.isEmpty(data.getIsSiteProve())) {
                mBinding.myMlSiteProve.setVisibility(TextUtils.equals(data.getSiteProve(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlSiteProve.addListRequest(StringUtils.splitPIC(data.getSiteProve()));
            }

            mBinding.mySlCarType.setTextByRequestByKey(data.getCarType());
            mBinding.myElSiteArea.setTextHint("");
            mBinding.myElSiteArea.setTextByRequest(data.getSiteArea());//场地面积
            mBinding.myElOtherPropertyNote.setTextHint("");//场地面积
            mBinding.myElOtherPropertyNote.setTextByRequest(data.getOtherPropertyNote());//其他


        } else {
            mBinding.mySlIsHouseProperty.setContentByKey(data.getIsHouseProperty());
            if (!TextUtils.isEmpty(data.getIsHouseProperty())) {
                mBinding.myMlHouseProperty.setVisibility(TextUtils.equals(data.getIsHouseProperty(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlHouseProperty.addList(StringUtils.splitPIC(data.getHouseProperty()));
            }

            mBinding.mySlIsLicense.setContentByKey(data.getIsLicense());
            if (!TextUtils.isEmpty(data.getIsLicense())) {
                mBinding.myMlLicense.setVisibility(TextUtils.equals(data.getIsLicense(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlLicense.addList(StringUtils.splitPIC(data.getLicense()));
            }

            mBinding.mySlIsDriceLicense.setContentByKey(data.getIsDriceLicense());
            if (!TextUtils.isEmpty(data.getIsDriceLicense())) {
                mBinding.myMlDriceLicense.setVisibility(TextUtils.equals(data.getIsDriceLicense(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlDriceLicense.addList(StringUtils.splitPIC(data.getDriceLicense()));
            }

            mBinding.mySlIsSiteProve.setContentByKey(data.getIsSiteProve());
            if (!TextUtils.isEmpty(data.getIsSiteProve())) {
                mBinding.myMlSiteProve.setVisibility(TextUtils.equals(data.getSiteProve(), "0") ? View.GONE : View.VISIBLE);
                mBinding.myMlSiteProve.addList(StringUtils.splitPIC(data.getSiteProve()));
            }

            mBinding.mySlCarType.setContentByKey(data.getCarType());
            mBinding.myElSiteArea.setText(data.getSiteArea());//场地面积
            mBinding.myElOtherPropertyNote.setText(data.getOtherPropertyNote());//其他

        }
    }

    @NonNull
    private List<DataDictionary> getDataDictionaries() {
        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey("0").setDvalue("无"));
        data.add(new DataDictionary().setDkey("1").setDvalue("有"));
        return data;
    }

    @NonNull
    private List<DataDictionary> getCarType() {
        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey("0").setDvalue("自有"));
        data.add(new DataDictionary().setDkey("1").setDvalue("租用"));
        data.add(new DataDictionary().setDkey("2").setDvalue("无"));
        return data;
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

                if (requestCode == mBinding.myMlHouseProperty.getRequestCode()) {
                    mBinding.myMlHouseProperty.addList(key);
                }

                if (requestCode == mBinding.myMlLicense.getRequestCode()) {
                    mBinding.myMlLicense.addList(key);
                }

                if (requestCode == mBinding.myMlDriceLicense.getRequestCode()) {
                    mBinding.myMlDriceLicense.addList(key);
                }

                if (requestCode == mBinding.myMlSiteProve.getRequestCode()) {
                    mBinding.myMlSiteProve.addList(key);
                }

                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    private String checkFail;

    public boolean check() {
        if (mBinding.mySlIsHouseProperty.check()) {
            checkFail = mBinding.mySlIsHouseProperty.getTitle();
            return false;
        }

        if (mBinding.mySlIsLicense.check()) {
            checkFail = mBinding.mySlIsLicense.getTitle();
            return false;
        }

        if (mBinding.mySlIsDriceLicense.check()) {
            checkFail = mBinding.mySlIsDriceLicense.getTitle();
            return false;
        } else {
            if (TextUtils.equals(data.getShopWay(), "2")) {
                //二手车  必填

                if (TextUtils.isEmpty(mBinding.myMlDriceLicense.getListData())) {

                    checkFail = mBinding.mySlIsDriceLicense.getTitle();
                    return false;
                }

            }
        }


        return true;
    }

    @Subscribe
    public void doCheck(BudgetCheckModel model) {

        if (model == null)
            return;

        // 检查未通过，由Activity提示，不往下check
        if (!model.isCheckResult()) {
            return;
        }

        if (model.getCheckIndex() == 2) {
            if (check()) {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex() + 1)
                        .setCheckResult(true)
                        .setCheckFail(null));

            } else {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex())
                        .setCheckResult(false)
                        .setCheckFail(checkFail));

            }
        }

    }

    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<>();

        map.put("isHouseProperty", mBinding.mySlIsHouseProperty.getDataKey());
        map.put("houseProperty", mBinding.myMlHouseProperty.getListData());
        map.put("isLicense", mBinding.mySlIsLicense.getDataKey());
        map.put("license", mBinding.myMlLicense.getListData());
        map.put("carType", mBinding.mySlCarType.getDataKey());
        map.put("isDriceLicense", mBinding.mySlIsDriceLicense.getDataKey());

        map.put("driceLicense", mBinding.myMlDriceLicense.getListData());
        map.put("isSiteProve", mBinding.mySlIsSiteProve.getDataKey());
        map.put("siteProve", mBinding.myMlSiteProve.getListData());
        map.put("siteArea", mBinding.myElSiteArea.getText());
        map.put("otherPropertyNote", mBinding.myElOtherPropertyNote.getText());

        return map;
    }
}
