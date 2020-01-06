package com.cdkj.wzcd.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.MainFrgCreditBinding;
import com.cdkj.wzcd.main.credit.CreditPageActivity;
import com.cdkj.wzcd.main.credit.CreditPledgePageActivity;
import com.cdkj.wzcd.main.credit.adapter.CreditAdapter;
import com.cdkj.wzcd.main.credit.bean.CreditBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:28
 */
public class MainCreditFragment extends BaseLazyFragment {

    private MainFrgCreditBinding mBinding;

    private List<CreditBean> list = new ArrayList<>();

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MainCreditFragment getInstance() {
        MainCreditFragment fragment = new MainCreditFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_frg_credit, null, false);

        initDatas();
        initAdapter();

        return mBinding.getRoot();
    }

    private void initDatas() {

        list.add(new CreditBean("zrzl", "准入资料", R.mipmap.credit_zrzl));
        list.add(new CreditBean("lrsh", "录入审核", R.mipmap.credit_lrsh));
        list.add(new CreditBean("yksq", "用款申请", R.mipmap.credit_yksq));
        list.add(new CreditBean("yksh", "用款审核", R.mipmap.credit_yksh));
        list.add(new CreditBean("zdhl", "制单回录", R.mipmap.credit_zdhl));
        list.add(new CreditBean("dzhl", "垫资回录", R.mipmap.credit_dzhl));
        list.add(new CreditBean("lj", "理件", R.mipmap.credit_lj));
        list.add(new CreditBean("dj", "打件", R.mipmap.credit_dj));
        list.add(new CreditBean("yhsj", "银行收件", R.mipmap.credit_yhsj));
        list.add(new CreditBean("yhtj", "银行提交", R.mipmap.credit_yhtj));
        list.add(new CreditBean("lrfk", "录入放款", R.mipmap.credit_lrfk));
        list.add(new CreditBean("qrsk", "确认收款", R.mipmap.credit_qrsk));
        list.add(new CreditBean("fsdy", "发送抵押", R.mipmap.credit_fsdy));
        list.add(new CreditBean("qrdy", "确认抵押", R.mipmap.credit_qrdy));
        list.add(new CreditBean("rd", "入档", R.mipmap.credit_rd));

    }

    private void initAdapter() {

        CreditAdapter mAdapter = new CreditAdapter(list);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            itemClickListener(mAdapter.getItem(position));
        });

        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setLayoutManager(new GridLayoutManager(mActivity, 3));
    }

    private void itemClickListener(CreditBean item) {

        ArrayList<String> nodeList = new ArrayList<String>();

        switch (item.getCode()) {

            case "zrzl":
                nodeList.clear();
                nodeList.add("a1");
                nodeList.add("a1x");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "lrsh":
                nodeList.clear();
                nodeList.add("a2");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "yksq":
                nodeList.clear();
                nodeList.add("b1");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "yksh":
                nodeList.clear();
                nodeList.add("b3");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "zdhl":
                nodeList.clear();
                nodeList.add("b4");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "dzhl":
                nodeList.clear();
                nodeList.add("b5");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "lj":
                nodeList.clear();
                nodeList.add("c1");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "dj":
                nodeList.clear();
                nodeList.add("c2");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "yhsj":
                nodeList.clear();
                nodeList.add("d1");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "yhtj":
                nodeList.clear();
                nodeList.add("d2");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "lrfk":
                nodeList.clear();
                nodeList.add("d3");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "qrsk":
                nodeList.clear();
                nodeList.add("d4");
                CreditPageActivity.open(mActivity, nodeList);
                break;

            case "fsdy":
                nodeList.clear();
                nodeList.add("e1");
                CreditPledgePageActivity.open(mActivity, nodeList);
                break;

            case "qrdy":
                nodeList.clear();
                nodeList.add("e2");
                CreditPledgePageActivity.open(mActivity, nodeList);
                break;

            case "rd":
                nodeList.clear();
                nodeList.add("f1");
                CreditPageActivity.open(mActivity, nodeList);
                break;

        }

    }
}
