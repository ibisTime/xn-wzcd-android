package com.cdkj.wzcd;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cdkj.baselibrary.adapters.ViewPagerAdapter;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.eventmodels.EventFinishAll;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adapter.MainBottomTabAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActMainBinding;
import com.cdkj.wzcd.main.MainClientFragment;
import com.cdkj.wzcd.main.MainCreditFragment;
import com.cdkj.wzcd.main.MainMessageFragment;
import com.cdkj.wzcd.main.MainUserFragment;
import com.cdkj.wzcd.model.NavigationBean;
import com.cdkj.wzcd.model.NodeModel;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.util.NodeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:26
 */
public class MainActivity2 extends AbsBaseLoadActivity {

    private ActMainBinding mBinding;

    // 节点列表
    public static List<NodeModel> BASE_NODE_LIST = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();
    public List<NavigationBean> navigationList = new ArrayList<>();


    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity2.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_main, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        init();

        initTabList();
        initTab();

        initFragments();
        initViewPager();
    }

    private void init() {
        NodeHelper.getNodeBaseDataRequest(this, "", "", new NodeHelper.NodeInterface() {
            @Override
            public void onSuccess(List<NodeModel> list) {
                BASE_NODE_LIST.clear();
                BASE_NODE_LIST.addAll(list);

            }

            @Override
            public void onReqFailure(String errorCode, String errorMessage) {

            }
        });

        /**
         * 获取用户信息
         */

        if (!SPUtilHelper.isLoginNoStart()) {  //没有登录不用请求
            return;
        }

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getUserInfoDetails("630067", StringUtils.getJsonToString(map));

        addCall(call);
        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserModel>(this) {
            @Override
            protected void onSuccess(UserModel data, String SucMessage) {
                SPUtilHelper.saveisTradepwdFlag(data.isTradepwdFlag());
                SPUtilHelper.saveUserPhoneNum(data.getMobile());
                SPUtilHelper.saveUserName(data.getRealName());
                SPUtilHelper.saveUserNickName(data.getNickname());
                SPUtilHelper.saveUserPhoto(data.getPhoto());
                SPUtilHelper.saveRoleCode(data.getRoleCode());
                SPUtilHelper.saveUserCompanyCode(data.getCompanyCode());
                SPUtilHelper.saveTeamCode(data.getTeamCode());
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                UITipDialog.showFail(MainActivity2.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initTabList() {
        NavigationBean bean1 = new NavigationBean();
        bean1.setName("首页");
        bean1.setPic(R.mipmap.main_credit);
        bean1.setDarkPic(R.mipmap.main_credit_un);
        bean1.setMainSelect(true);
        navigationList.add(bean1);

        NavigationBean bean2 = new NavigationBean();
        bean2.setName("客户");
        bean2.setPic(R.mipmap.main_client);
        bean2.setDarkPic(R.mipmap.main_client_un);
        navigationList.add(bean2);

        NavigationBean bean3 = new NavigationBean();
        bean3.setName("消息");
        bean3.setPic(R.mipmap.main_message);
        bean3.setDarkPic(R.mipmap.main_message_un);
        navigationList.add(bean3);

        NavigationBean bean4 = new NavigationBean();
        bean4.setName("我的");
        bean4.setPic(R.mipmap.main_user);
        bean4.setDarkPic(R.mipmap.main_user_un);
        navigationList.add(bean4);
    }

    private void initTab() {

        MainBottomTabAdapter adapter = new MainBottomTabAdapter(navigationList);
        adapter.setOnItemClickListener((adapter1, view, position) -> {

            for (NavigationBean bean : adapter.getData()) {
                bean.setMainSelect(false);
            }
            adapter.getData().get(position).setMainSelect(true);
            adapter.notifyDataSetChanged();

            setShowIndex(position);
        });

        mBinding.rv.setAdapter(adapter);
        mBinding.rv.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
    }

    private void initFragments() {
        fragments.add(MainCreditFragment.getInstance());
        fragments.add(MainClientFragment.getInstance());
        fragments.add(MainMessageFragment.getInstance());
        fragments.add(MainUserFragment.getInstance());
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mBinding.pagerMain.setPagingEnabled(false);//禁止左右切换
        mBinding.pagerMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.pagerMain.setOffscreenPageLimit(fragments.size());
        mBinding.pagerMain.setCurrentItem(0);
    }

    /**
     * 设置要显示的界面
     *
     * @param index
     */
    private void setShowIndex(int index) {

        mBinding.pagerMain.setCurrentItem(index, false);
    }

    @Override
    public void onBackPressed() {
        showDoubleWarnListen("确认退出？", view -> {
            EventBus.getDefault().post(new EventFinishAll()); //结束所有界面
            finish();
        });
    }

}
