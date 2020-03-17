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

import com.cdkj.baselibrary.activitys.ImageSelectActivity2;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.adapter.BaseImageAdapter;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.interfaces.BaseImageInterface;
import com.cdkj.wzcd.databinding.LayoutBaseImageBinding;
import com.cdkj.wzcd.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;

import static com.cdkj.baselibrary.utils.CameraHelper.CAMERA_TYPE_IMAGE;
import static com.cdkj.baselibrary.utils.CameraHelper.CAMERA_TYPE_VIDEO;

/**
 * @author : qianLei
 * @since : 2019/12/28 19:58
 */
public class BaseImageLayout extends LinearLayout {

    // 正常：点击选择，可删除
    public static String MODEL_NORMAL = "0";
    // 预览：点击预览，不可删除
    public static String MODEL_PREVIEW = "1";
    // 身份证模式：点击预览，可删除
    public static String MODEL_ID = "2";

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

    // item点击模式
    private String clickModel;

    private boolean selectMultiple;//是否要多选

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
        selectMultiple = typedArray.getBoolean(R.styleable.BaseImageLayout_selectMultiple, false);
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

        clickModel = MODEL_NORMAL;
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

        clickModel = MODEL_NORMAL;
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

        clickModel = MODEL_NORMAL;
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

        clickModel = MODEL_NORMAL;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置数据  单选数据设置
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
     * 设置多选数据  多选设置完还可以再添加
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

            if (MODEL_NORMAL.equals(clickModel)) {

                field = item.getField();
                ImageSelectActivity2.launch((Activity) context, field + "_" + position, 0, false, cameraType, selectMultiple);

            } else if (MODEL_PREVIEW.equals(clickModel)) {

                if (mActivity == null) {
                    return;
                }
                BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(
                        mActivity)
                        .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
                photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + item.getPic());
                mActivity.startActivity(photoPreviewIntentBuilder.build());

            } else if (MODEL_ID.equals(clickModel)) {

                if (null == item || TextUtils.isEmpty(item.getPic())) {

                    // 选择图片
                    field = item.getField();
                    ImageSelectActivity2.launch((Activity) context, field + "_" + position, 0, false, cameraType, selectMultiple);

                } else {

                    // 预览
                    if (mActivity == null) {
                        return;
                    }
                    BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(
                            mActivity)
                            .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
                    photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + item.getPic());
                    mActivity.startActivity(photoPreviewIntentBuilder.build());

                }



            }

        });


        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            if (MODEL_NORMAL.equals(clickModel)) {

                BaseImageBean item = mAdapter.getItem(position);

                if (isMultiple) {
                    mList.remove(position);
                } else {
                    item.setPic(null);
                    mList.remove(position);
                    mList.add(position, item);
                }

                mAdapter.notifyDataSetChanged();

            } else if (MODEL_PREVIEW.equals(clickModel)) {

                // 不可删除

            } else if (MODEL_ID.equals(clickModel)) {

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
        //field==eventname==id (eventname是传递给选择界面的) 每个view都要初始化不同的整个field  如果不判断直接add到集合中  那么同界面的所有的view都会加入照片
        //没activity中调用initMultiple初始化这个view  并传入不同的field
        //同时field还是接口的入参key
        if (tags[0].equals(field)) {

            Object value = bean.getValue();
            ArrayList<String> netImgUrls;
            if (value instanceof List) {
                netImgUrls = (ArrayList) value;
            } else {
                return;
            }


            if (selectMultiple) {
                for (String url : netImgUrls) {
                    BaseImageBean emptyImageBean = new BaseImageBean();
                    emptyImageBean.setField(field);
                    emptyImageBean.setPic(url);
                    mList.add(0, emptyImageBean);
                }
                mAdapter.notifyDataSetChanged();
                //通知设置的回调
                if (null != imageInterface) {
                    imageInterface.upload(Integer.parseInt(tags[1]), tags[0], getData());
                }
                return;

            } else {

                if (isMultiple) {
                    //如果是多选的话  再后面增加  一个空的加号图片
                    if (Integer.parseInt(tags[1]) == mList.size() - 1) {
                        BaseImageBean emptyImageBean = new BaseImageBean();
                        emptyImageBean.setField(field);
                        mList.add(emptyImageBean);
                    }
                }
                mAdapter.notifyDataSetChanged();

                mList.get(Integer.parseInt(tags[1])).setPic(bean.getValue1());
                //通知设置的回调
                if (null != imageInterface) {
                    imageInterface.upload(Integer.parseInt(tags[1]), tags[0], bean.getValue1());
                }
            }

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
            if (!TextUtils.isEmpty(bean.getPic())) {
                pic = pic + bean.getPic() + "||";
            }
        }

        return pic.length() > 2 ? pic.substring(0, pic.length() - 2) : "";
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

    /**
     * 检查有没有填写内容  非必填的不检查,返回false为通过  true为不通过
     *
     * @return
     */
    public boolean check() {

        if (!isRequired) {
            return false;
        }
        if (isMultiple) {
            if (TextUtils.isEmpty(getData())) {
                ToastUtil.show(context, "请上传" + txtTitle);
                return true;
            }
        } else {
            for (BaseImageBean bean : mList) {

                if (bean.isRequired() && TextUtils.isEmpty(bean.getPic())) {
                    ToastUtil.show(context, "请上传" + bean.getTitle());
                    return true;
                }

            }
        }

        return false;

    }

    public void setClickModel(String clickModel) {

        this.clickModel = clickModel;

        if (MODEL_PREVIEW.equals(clickModel)) {

            Iterator<BaseImageBean> iterator = mList.iterator();
            while (iterator.hasNext()) {
                BaseImageBean bean = iterator.next();
                bean.setDetail(true);
                if (TextUtils.isEmpty(bean.getPic())) {
                    iterator.remove();
                }
            }

            mAdapter.notifyDataSetChanged();

        }

    }

    /**
     * 获取是否必填项
     *
     * @return
     */
    public boolean getisRequired() {
        return isRequired;
    }

    /**
     * 此方法只适用于
     */
    public void clean() {
        if (mList != null && mList.size() > 0) {
            for (BaseImageBean baseImageBean : mList) {
                baseImageBean.setPic("");
            }
            if (isMultiple || selectMultiple) {
                mList.clear();
                initMultiple(mActivity, mList, field);
            } else {
//                init(mActivity, mList);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
