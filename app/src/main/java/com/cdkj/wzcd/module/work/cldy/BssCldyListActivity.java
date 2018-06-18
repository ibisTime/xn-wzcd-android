package com.cdkj.wzcd.module.work.cldy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cdkj.baselibrary.base.AbsTabLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆抵押
 */
public class BssCldyListActivity extends AbsTabLayoutActivity {

    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    /**
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BssCldyListActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("车辆抵押");

        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        initViewPagerData();
    }

    @Override
    public List<Fragment> getFragments() {
        return mFragmentList;
    }

    @Override
    public List<String> getFragmentTitles() {
        return mTitleList;
    }

    private void initViewPagerData() {

        mTitleList.add("待办事宜");
        mFragmentList.add(CldyListFragment.getInstance(true, ""));

        mTitleList.add("我的申请");
        mFragmentList.add(CldyListFragment.getInstance(false, ""));

        initViewPager();
        mTabLayoutBinding.viewpager.setOffscreenPageLimit(2);
        mTabLayoutBinding.tablayout.setTabMode(TabLayout.MODE_FIXED);

        mTabLayoutBinding.tablayout.setVisibility(View.GONE);
    }
}
