package com.cdkj.wzcd.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.adapters.TablayoutAdapter;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsTabLayoutFragment;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.MainFrgMessageBinding;
import com.cdkj.wzcd.databinding.MainFrgUserBinding;
import com.cdkj.wzcd.main.message.AgendaFragment;
import com.cdkj.wzcd.main.message.MessageFragment;
import com.cdkj.wzcd.main.message.NoticeFragment;
import com.cdkj.wzcd.model.UserModel;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:28
 */
public class MainMessageFragment extends BaseLazyFragment {

    private MainFrgMessageBinding mBinding;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MainMessageFragment getInstance() {
        MainMessageFragment fragment = new MainMessageFragment();
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_frg_message, null, false);

        getUserInfoRequest();

        return mBinding.getRoot();
    }



    /**
     * 获取用户信息
     */
    public void getUserInfoRequest() {

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils
                .createApi(MyApiServer.class)
                .getUserInfoDetails("630067", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserModel>(mActivity) {
            @Override
            protected void onSuccess(UserModel data, String SucMessage) {
                initViewPager(data);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                UITipDialog.showFail(mActivity, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    protected void initViewPager(UserModel data) {

        TablayoutAdapter tablayoutAdapter = new TablayoutAdapter(getChildFragmentManager());

        List<String> mTitles = new ArrayList<>();
        mTitles.add("代办事项");
        mTitles.add("消息");
        mTitles.add("系统公告");

        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(AgendaFragment.getInstance());
        mFragments.add(NoticeFragment.getInstance());
        mFragments.add(MessageFragment.getInstance(data.getDepartmentCode()));

        tablayoutAdapter.addFrag(mFragments, mTitles);

        mBinding.viewpager.setAdapter(tablayoutAdapter);
        mBinding.tb.setupWithViewPager(mBinding.viewpager);        //viewpager和tablayout关联
        mBinding.viewpager.setOffscreenPageLimit(tablayoutAdapter.getCount());
    }

}
