package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.BaseImageLayout;
import com.cdkj.wzcd.custom.BaseSelectLayout;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.GPSBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.GPSSaleBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import retrofit2.Call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/31 23:39
 */
public class GPSAdapter extends BaseQuickAdapter<GPSBean, BaseViewHolder> {

    private boolean isDetail;
    private Activity mActivity;
    private List<DataDictionary> selectList;

    public GPSAdapter(@Nullable List<GPSBean> data, List<DataDictionary> selectList,
            boolean isDetail, Activity mActivity) {
        super(R.layout.item_zrzl_gps, data);
        this.isDetail = isDetail;
        this.mActivity = mActivity;
        this.selectList = selectList;
    }

    @Override
    protected void convert(BaseViewHolder helper, GPSBean item) {

        BaseSelectLayout slCode = helper.getView(R.id.sl_code);
        slCode.setData(selectList, (dialog, which) -> {
            item.setCode(slCode.getDataKey());
            item.setGpsDevNo(slCode.getDataValue());
        });
        slCode.setListener(view -> {
            if (isDetail) {
                return;
            }
            getGPSSaleList(slCode, item);
        });
        slCode.setTextAndKey(item.getCode(), item.getGpsDevNo());

        BaseImageLayout ilAzPhotos = helper.getView(R.id.il_azPhotos);
        if (item.isInit()) {
            ilAzPhotos.initMultiple(mActivity,item.getAzPhotos(), "azPhotos" + helper.getLayoutPosition());
            item.setInit(false);
        }
        ilAzPhotos.setImageInterface((location, field, key) -> {
            item.setAzPhotos(ilAzPhotos.getData());
        });
        if (isDetail) {
            slCode.setOnClickEnable(false);
            ilAzPhotos.setOnClickEnable(false);
        }
    }

    public void getGPSSaleList(BaseSelectLayout slCode, GPSBean item) {

        Map<String, String> map = new HashMap<>();
        map.put("useStatus", "1");
        map.put("applyUser", item.getApplyUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getGPSSaleList("632707", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseListCallBack<GPSSaleBean>(mContext) {

            @Override
            protected void onSuccess(List<GPSSaleBean> data, String SucMessage) {

                selectList.clear();
                for (GPSSaleBean bean : data) {
                    selectList.add(new DataDictionary().setDkey(bean.getCode())
                            .setDvalue(bean.getGpsDevNo()));
                }

                slCode.setData(selectList);
                slCode.showSelect();
            }

            @Override
            protected void onFinish() {
            }
        });
    }
}
