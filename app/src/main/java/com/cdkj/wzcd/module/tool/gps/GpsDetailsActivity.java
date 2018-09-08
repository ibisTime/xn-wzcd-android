package com.cdkj.wzcd.module.tool.gps;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.GpsDetailsAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsDetailsBinding;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.gps_apply_status;

/**
 * gps申领
 */
public class GpsDetailsActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityGpsDetailsBinding mBinding;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, GpsDetailsActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_details, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("详情");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getGPS();
    }

    private void getGPS() {

        Map<String, String> map = new HashMap<>();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGps("632716", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<GpsApplyModel>(this) {
            @Override
            protected void onSuccess(GpsApplyModel model, String SucMessage) {

                setView(model);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(GpsApplyModel model) {
        mBinding.myNlStatus.setText(DataDictionaryHelper.getValueBuyKey(gps_apply_status, model.getStatus()));
        mBinding.myNlNumber.setText(model.getApplyCount() + "个");
        mBinding.myNlReason.setText(model.getApplyReason());

        GpsDetailsAdapter mAdapter = new GpsDetailsAdapter(model.getGpsList());
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.recycler.setAdapter(mAdapter);

    }
}