package com.cdkj.wzcd.module.work.credit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cdkj.baselibrary.base.AbsTabLayoutActivity;
import com.cdkj.wzcd.util.UserHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 车贷业务（business）下，咨询调查列表Activity
 * Created by cdkj on 2018/5/29.
 */

public class CreditListActivity extends AbsTabLayoutActivity {

    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    /**
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditListActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("资信调查");

        if (!UserHelper.isZHRY()){
            mBaseBinding.titleView.setRightTitle("发起征信");
            mBaseBinding.titleView.setRightFraClickListener(view -> {
                CreditInitiateActivity.open(this, null);
            });
        }

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
        mFragmentList.add(CreditListFragment.getInstance(true, ""));

        mTitleList.add("我的申请");
        mFragmentList.add(CreditListFragment.getInstance(false, ""));

        initViewPager();
        mTabLayoutBinding.viewpager.setOffscreenPageLimit(2);
        mTabLayoutBinding.tablayout.setTabMode(TabLayout.MODE_FIXED);


//        if (UserHelper.isZHRY()){
            mTabLayoutBinding.tablayout.setVisibility(View.GONE);
//        }
    }
}
