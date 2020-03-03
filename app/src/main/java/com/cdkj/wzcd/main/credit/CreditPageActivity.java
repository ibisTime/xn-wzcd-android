package com.cdkj.wzcd.main.credit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.main.credit.adapter.CreditPageAdapter;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.credit.bean.JurisdictionBean;
import com.cdkj.wzcd.main.credit.module.djcz.DjczActivity;
import com.cdkj.wzcd.main.credit.module.dzhl.DzhlActivity;
import com.cdkj.wzcd.main.credit.module.ljdj.LjdjActivity;
import com.cdkj.wzcd.main.credit.module.lrfk.LrfkActivity;
import com.cdkj.wzcd.main.credit.module.qrpg.QrpgActivity;
import com.cdkj.wzcd.main.credit.module.qrsk.QrskActivity;
import com.cdkj.wzcd.main.credit.module.rd.RdActivity;
import com.cdkj.wzcd.main.credit.module.yhsj.YhsjActivity;
import com.cdkj.wzcd.main.credit.module.yhtj.YhtjActivity;
import com.cdkj.wzcd.main.credit.module.yksh.YkshActivity;
import com.cdkj.wzcd.main.credit.module.yksq.YksqActivity;
import com.cdkj.wzcd.main.credit.module.zdhl.ZdhlActivity;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : qianLei
 * @since : 2019/12/26 13:57
 */
public class CreditPageActivity extends AbsRefreshListActivity {

    public static String MATERIAL = "0";
    public static String CUR = "1";

    ArrayList<String> curNodeCodeList;
    private boolean isShowCrate;
    private String type;

    public static void open(Context context, ArrayList<String> nodeList, String type) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditPageActivity.class);
        intent.putStringArrayListExtra(CdRouteHelper.DATA_SIGN, nodeList);
        intent.putExtra(CdRouteHelper.DATA_SIGN3, type);
        context.startActivity(intent);
    }

    /**
     * 只有准入资料界面  再有权限的情况下会显示新建按钮 其他界面都不先是
     *
     * @param context
     * @param nodeList
     * @param isShowCrate 传入true+有权限 右上角就会显示 新建按钮
     */
    public static void open(Context context, ArrayList<String> nodeList, boolean isShowCrate, String type) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditPageActivity.class);
        intent.putStringArrayListExtra(CdRouteHelper.DATA_SIGN, nodeList);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, isShowCrate);
        intent.putExtra(CdRouteHelper.DATA_SIGN3, type);
        context.startActivity(intent);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        CreditPageAdapter mAdapter = new CreditPageAdapter(listData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            CreditPageBean item = mAdapter.getItem(position);
            if (!TextUtils.isEmpty(type) && MATERIAL.equals(type)) {
                onItemClickMaterial(item);
            } else {
                onItemClick(item);
            }


        });
        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, Object> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        boolean codeFlag = true;
        for (String code : curNodeCodeList) {
            if (code.equals("h1") || code.equals("h2")) {
                codeFlag = false;
                map.put("materialNodeCodeList", curNodeCodeList);
            }
        }

        if (codeFlag) {
            map.put("curNodeCodeList", curNodeCodeList);
        }


        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCreditPage("632515", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CreditPageBean>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<CreditPageBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无准入资料", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        init();

        initRefreshHelper(10);
        //获取用户权限  显示隐藏右上角的新建按钮
        if (isShowCrate) {
            getJurisdiction();
        }

    }

    private void getJurisdiction() {

//        Map<String, Object> map = RetrofitUtils.getNodeListMap();
        HashMap<String, String> map = new HashMap<>();
        map.put("roleCode", SPUtilHelper.getRoleCode());
        Call call = RetrofitUtils.createApi(MyApiServer.class).getJurisdiction("630167", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseListCallBack<JurisdictionBean>(null) {

            @Override
            protected void onSuccess(List<JurisdictionBean> data, String SucMessage) {
                if (data != null && data.size() > 0) {
                    for (JurisdictionBean datum : data) {
                        if (TextUtils.equals("a1", datum.getCode())) {
                            mBaseBinding.titleView.setRightTitle("新建");
                            mBaseBinding.titleView.setRightFraClickListener(view -> {
                                ZrzlActivity.open(CreditPageActivity.this, null);
                            });
                            break;
                        }
                    }
                }
            }

            @Override
            protected void onFinish() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRefreshHelper.onDefaultMRefresh(true);
    }

    private void init() {
        curNodeCodeList = getIntent().getStringArrayListExtra(CdRouteHelper.DATA_SIGN);
        isShowCrate = getIntent().getBooleanExtra(CdRouteHelper.DATA_SIGN2, false);
        type = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN3);

        if (curNodeCodeList.isEmpty()) {
            return;
        }

        for (String node : curNodeCodeList) {

            if (node.equals("a1") || node.equals("a1x")) {
                mBaseBinding.titleView.setMidTitle("准入资料");
            } else if (node.equals("a2")) {
                mBaseBinding.titleView.setMidTitle("准入审核");
            } else if (node.equals("h1")) {
                mBaseBinding.titleView.setMidTitle("确认评估");
            } else if (node.equals("h2")) {
                mBaseBinding.titleView.setMidTitle("接收评估");
            } else if (node.equals("b1")) {
                mBaseBinding.titleView.setMidTitle("用款申请");
            } else if (node.equals("b3")) {
                mBaseBinding.titleView.setMidTitle("用款审核");
            } else if (node.equals("b4")) {
                mBaseBinding.titleView.setMidTitle("制单回录");
            } else if (node.equals("b5")) {
                mBaseBinding.titleView.setMidTitle("垫资回录");
            } else if (node.equals("c1")) {
                mBaseBinding.titleView.setMidTitle("理件");
            } else if (node.equals("c2")) {
                mBaseBinding.titleView.setMidTitle("打件");
            } else if (node.equals("d1")) {
                mBaseBinding.titleView.setMidTitle("银行收件");
            } else if (node.equals("d2")) {
                mBaseBinding.titleView.setMidTitle("银行提交");
            } else if (node.equals("d3")) {
                mBaseBinding.titleView.setMidTitle("录入放款");
            } else if (node.equals("d4")) {
                mBaseBinding.titleView.setMidTitle("确认收款");
            } else if (node.equals("f2")) {
                mBaseBinding.titleView.setMidTitle("入档");
            }

        }

    }

    private void onItemClick(CreditPageBean item) {

        switch (item.getCurNodeCode()) {

            case "a1":
            case "a1x":
                ZrzlActivity.open(this, item.getCode());
                break;

            case "a2":
                ZrshActivity.open(this, item.getCode());
                break;

            case "b1":
                YksqActivity.open(this, item.getCode());
                break;

            case "b3":
                //用款审核
                YkshActivity.open(this, item.getCode());
                break;

            case "b4":
                ZdhlActivity.open(this, item.getCode());
                break;

            case "b5":
                DzhlActivity.open(this, item.getCode());
                break;

            case "c1":
                LjdjActivity.open(this, item.getCode());
                break;

            case "c2":
                DjczActivity.open(this, item.getCode());
                break;

            case "d1":
                YhsjActivity.open(this, item.getCode());
                break;

            case "d2":
                YhtjActivity.open(this, item.getCode());
                break;

            case "d3":
                LrfkActivity.open(this, item.getCode());
                break;

            case "d4":
                QrskActivity.open(this, item.getCode());
                break;

            case "f1":
                RdActivity.open(this, item.getCode());
                break;
        }

    }

    private void onItemClickMaterial(CreditPageBean item) {

        switch (item.getMaterialNodeCode()) {

            case "h1":
                QrpgActivity.open(this, item.getCode(), false);
                break;
            case "h2":
                QrpgActivity.open(this, item.getCode(), true);
                break;

        }

    }
}
