package com.cdkj.wzcd.main.credit.module.yksh;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActRwglBinding;
import com.cdkj.wzcd.main.credit.module.yksh.adapter.RwglAdapter;
import com.cdkj.wzcd.main.credit.module.yksh.bean.RwBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : qianLei
 * @since : 2020/1/1 22:13
 */
public class RwglActivity extends AbsBaseLoadActivity {

    private ActRwglBinding mBinding;

    private List<RwBean> list = new ArrayList<>();
    private RwglAdapter mAdapter;

    public static void open(Context context, List<RwBean> list) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RwglActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, (Serializable) list);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_rwgl, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("任务管理");

        mBaseBinding.titleView.setRightTitle("新增");
        mBaseBinding.titleView.setRightFraClickListener(view -> {
            RwActivity.open(this, null);
        });

        init();
        initAdapter();
        initListener();
    }

    private void init() {
        List<RwBean> list = (List<RwBean>) getIntent()
                .getSerializableExtra(CdRouteHelper.DATA_SIGN);

        if (null != list && !list.isEmpty()) {
            this.list.addAll(list);
        }
    }

    private void initAdapter() {

        mAdapter = new RwglAdapter(list);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            RwBean item = mAdapter.getItem(position);
            item.setPosition(position);
            RwActivity.open(this, item);
        });

        mBinding.rvRw.setAdapter(mAdapter);
        mBinding.rvRw.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void initListener() {
        mBinding.btnConfirm.setOnConfirmListener(view -> {
            EventBus.getDefault().post(new EventBean().setTag("yksh_rw_list").setValue(list));
            finish();
        });
    }

    @Subscribe
    public void rw(EventBean bean) {

        if (bean.getTag().equals("yksh_rw_add")) {

            list.add((RwBean) bean.getValue());
            mAdapter.notifyDataSetChanged();

        }

        if (bean.getTag().equals("yksh_rw_modify")) {

            RwBean rwBean = (RwBean) bean.getValue();

            list.remove(rwBean.getPosition());
            list.add(rwBean.getPosition(), rwBean);
            mAdapter.notifyDataSetChanged();

        }

    }

}
