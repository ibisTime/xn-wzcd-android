package com.cdkj.wzcd.main.credit.module.zrzl.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActShuidanBinding;
import com.cdkj.wzcd.databinding.FrgDzxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.CarBrandActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.SdAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import java.util.ArrayList;
import java.util.List;

import static com.cdkj.baselibrary.utils.StringUtils.splitAsPicList;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailSdActivity extends AbsBaseLoadActivity {


    private ActShuidanBinding mBinding;

    private String keyName;

    private String keyValue;
    private List<String> pic = new ArrayList<>();

    public static void open(Context context, String keyName, ZrzlBean data) {
        if (context == null) {
            return;
        }

        String url = null;
        String keyValue = null;

        List<ZrzlBean.AttachmentsBean> attachments = data.getAttachments();
        for (ZrzlBean.AttachmentsBean bean : attachments) {

            if (bean.getKname().equals(keyName)) {

                url = bean.getUrl();
                keyValue = bean.getVname();
                break;

            }

        }

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(keyValue)) {
            ToastUtil.show(context, "暂无材料");
            return;
        }

        Intent intent = new Intent(context, DetailSdActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, url);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, keyValue);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_shuidan, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        init();
        setData();
    }

    private void init() {

        pic.addAll(splitAsPicList(getIntent().getStringExtra(CdRouteHelper.DATA_SIGN)));
        keyValue = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN2);

        mBaseBinding.titleView.setMidTitle(keyValue);
    }

    private void setData() {
        SdAdapter mAdapter = new SdAdapter(pic);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            String pic = mAdapter.getItem(position);
            BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(
                    this)
                    .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
            photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + pic );
            startActivity(photoPreviewIntentBuilder.build());
        });

        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setLayoutManager(new GridLayoutManager(this, 2));
    }

}
