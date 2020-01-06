package com.cdkj.wzcd.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.adapter.BaseImageAdapter;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.interfaces.BaseImageInterface;
import com.cdkj.wzcd.databinding.LayoutBaseImageBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.util.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.*;

import static com.cdkj.baselibrary.utils.CameraHelper.CAMERA_TYPE_IMAGE;
import static com.cdkj.baselibrary.utils.CameraHelper.CAMERA_TYPE_VIDEO;

/**
 * @author : qianLei
 * @since : 2019/12/28 19:58
 */
public class BaseImageLayout extends LinearLayout {

    private LayoutBaseImageBinding mBinding;

    private Context context;
    private Activity mActivity;
    private boolean isMultiple;

    private String field;
    private String txtTitle;
    private boolean isRequired;
    private String cameraType;

    private BaseImageAdapter mAdapter;
    private List<BaseImageBean> mList = new ArrayList<>();

    private BaseImageInterface imageInterface;

    private boolean isOnClickEnable = true;

    public BaseImageLayout(Context context) {
        this(context, null);
    }

    public BaseImageLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseImageLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.BaseImageLayout_title);
        isRequired = typedArray.getBoolean(R.styleable.BaseImageLayout_isRequired, false);
        cameraType = typedArray.getString(R.styleable.BaseImageLayout_cameraType);

        typedArray.recycle();

        init(context);
        setData();
        setAdapter();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_image, this, true);

        if (TextUtils.isEmpty(cameraType)) {
            cameraType = CAMERA_TYPE_IMAGE;
        } else {
            switch (cameraType) {

                case "0":
                    cameraType = CAMERA_TYPE_IMAGE;
                    break;

                case "1":
                    cameraType = CAMERA_TYPE_VIDEO;
                    break;
            }
        }

        EventBus.getDefault().register(this);
    }

    private void setData() {
        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);
        mBinding.tvTitle.setText(txtTitle);

        mBinding.tvTitle.setOnClickListener(view -> {
            EventBus.getDefault().post("asdasda");
        });
    }

    /**
     * 多个单选
     *
     * @param list
     */
    public void init(Activity activity, List<BaseImageBean> list) {
        mActivity = activity;
        isMultiple = false;
        mList.addAll(list);

        isOnClickEnable = true;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 单个多选
     */
    public void initMultiple(Activity activity, String field) {
        mActivity = activity;
        isMultiple = true;
        this.field = field;
        mBinding.tvTitle.setText(txtTitle + "（多选）");

        BaseImageBean emptyImageBean = new BaseImageBean();
        emptyImageBean.setField(field);
        mList.add(emptyImageBean);

        isOnClickEnable = true;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 已添加过的单个多选
     *
     * @param pic
     */
    public void initMultiple(Activity activity, String pic, String field) {
        mActivity = activity;
        isMultiple = true;
        this.field = field;
        mBinding.tvTitle.setText(txtTitle + "（多选）");

        if (!TextUtils.isEmpty(pic)) {

            List<String> list = StringUtils.splitPIC(pic);
            for (String s : list) {
                BaseImageBean bean = new BaseImageBean();
                bean.setPic(s);
                bean.setField(s);
                mList.add(bean);
            }


        }
        BaseImageBean emptyImageBean = new BaseImageBean();
        emptyImageBean.setField(field);
        mList.add(emptyImageBean);

        isOnClickEnable = true;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 已添加过的单个多选
     *
     * @param list
     */
    public void initMultiple(Activity activity, List<BaseImageBean> list, String field) {
        mActivity = activity;
        isMultiple = true;
        this.field = field;
        mBinding.tvTitle.setText(txtTitle + "（多选）");

        if (!list.isEmpty()) {
            mList.addAll(list);
        }
        BaseImageBean emptyImageBean = new BaseImageBean();
        emptyImageBean.setField(field);
        mList.add(emptyImageBean);

        isOnClickEnable = true;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置数据，仅展示
     *
     * @param list
     */
    public void setDatas(List<BaseImageBean> list) {
        isMultiple = false;
        mList.addAll(list);

        isOnClickEnable = false;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置数据
     *
     * @param field
     * @param pic
     */
    public void setData(String field, String pic) {

        for (BaseImageBean bean : mList) {
            if (bean.getField().equals(field)) {
                bean.setPic(pic);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置多选数据
     *
     * @param pic
     */
    public void setData(String pic) {

        mList.clear();
        if (!TextUtils.isEmpty(pic)) {

            List<String> list = StringUtils.splitPIC(pic);
            for (String s : list) {
                BaseImageBean bean = new BaseImageBean();
                bean.setPic(s);
                bean.setField(s);
                mList.add(bean);
            }

        }
        BaseImageBean emptyImageBean = new BaseImageBean();
        emptyImageBean.setField(field);
        mList.add(emptyImageBean);

        mAdapter.notifyDataSetChanged();
    }

    public void setData(List<String> pic) {
        isMultiple = false;

        if (pic.size() != mList.size()) {
            LogUtil.E("请核对 " + field + " 数据长度");
            return;
        }

        for (int i = 0; i < mList.size(); i++) {

            mList.get(i).setPic(pic.get(i));

        }
        mAdapter.notifyDataSetChanged();

    }

    public void setImageInterface(BaseImageInterface imageInterface) {
        this.imageInterface = imageInterface;
    }

    private void setAdapter() {
        mAdapter = new BaseImageAdapter(mList);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            BaseImageBean item = mAdapter.getItem(position);

            if (isOnClickEnable) {

                field = item.getField();
                ImageSelectActivity
                        .launch((Activity) context, field + "_" + position, 0, false, cameraType);

            } else {
                if (mActivity == null) {
                    return;
                }
                BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(
                        mActivity)
                        .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
                photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + item.getPic());
                mActivity.startActivity(photoPreviewIntentBuilder.build());
            }

        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            if (isOnClickEnable) {
                BaseImageBean item = mAdapter.getItem(position);

                if (isMultiple) {
                    mList.remove(position);
                } else {
                    item.setPic(null);
                    mList.remove(position);
                    mList.add(position, item);
                }

                mAdapter.notifyDataSetChanged();
            }

        });

        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    @Subscribe
    public void upload(EventBean bean) {

        String[] tags = bean.getTag().split("_");

        if (tags.length < 2) {
            return;
        }

        if (tags[0].equals(field)) {

            mList.get(Integer.parseInt(tags[1])).setPic(bean.getValue1());

            if (null != imageInterface) {
                imageInterface.upload(Integer.parseInt(tags[1]), tags[0], bean.getValue1());
            }

            if (isMultiple) {

                if (Integer.parseInt(tags[1]) == mList.size() - 1) {
                    BaseImageBean emptyImageBean = new BaseImageBean();
                    emptyImageBean.setField(field);
                    mList.add(emptyImageBean);
                }

            }
            mAdapter.notifyDataSetChanged();

        }

    }


    public String getDataByField(String field) {
        String pic = "";

        for (BaseImageBean bean : mList) {

            if (bean.getField().equals(field)) {
                pic = bean.getPic();
            }

        }

        return pic;
    }

    public String getData() {
        String pic = "";
        for (BaseImageBean bean : mList) {
            pic = pic + bean.getPic() + "||";
        }

        return pic.substring(0, pic.length() - 2);
    }

    public Map<String, String> getMap() {

        Map<String, String> map = new LinkedHashMap<>();

        if (isMultiple) {

            String pic = "";
            for (BaseImageBean bean : mList) {
                if (!TextUtils.isEmpty(bean.getPic())) {
                    pic = pic + bean.getPic() + "||";
                }
            }

            if (TextUtils.isEmpty(pic)) {
                map.put(field, null);
            } else {
                map.put(field, pic.substring(0, pic.length() - 2));
            }


        } else {

            for (BaseImageBean bean : mList) {
                map.put(bean.getField(), bean.getPic());
            }

        }

        return map;
    }

    public boolean check() {

        if (!isRequired) {
            return false;
        }

        for (BaseImageBean bean : mList) {

            if (TextUtils.isEmpty(bean.getPic())) {
                ToastUtil.show(context, "请上传" + bean.getTitle());
                return true;
            }

        }

//        if (isMultiple) {
//
//
//
//
//        } else {
//
//            if (mList.size() == 0 || TextUtils.isEmpty(mList.get(0).getPic())) {
//                ToastUtil.show(context, "请上传至少一份" + txtTitle);
//                return true;
//            }
//
//        }

        return false;

    }

    public void setOnClickEnable(boolean isOnClickEnable) {

        this.isOnClickEnable = isOnClickEnable;

        if (!isOnClickEnable) {

            Iterator<BaseImageBean> iterator = mList.iterator();
            while (iterator.hasNext()) {
                BaseImageBean bean = iterator.next();
                bean.setDetail(!isOnClickEnable);
                if (TextUtils.isEmpty(bean.getPic())) {
                    iterator.remove();
                }
            }

            mAdapter.notifyDataSetChanged();

        }

    }

}
