package com.cdkj.baselibrary.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.base.BaseActivity;
import com.cdkj.baselibrary.interfaces.CameraPhotoListener2;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.CameraHelper2;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 打开相机 相册 图片裁剪 功能
 */
public class ImageSelectActivity2 extends BaseActivity implements View.OnClickListener,
        CameraPhotoListener2 {

    private String eventName = "";
    private String cameraType;

    private TextView tv_take_capture;// 拍照
    private TextView tv_alumb;// 相册选取
    private TextView tv_cancle;// 取消
    private View empty_view;// 取消

    public static final int SHOWPIC = 1; //显示拍照按钮
    public static final int SHOWALBUM = 2;//显示相册

    private CameraHelper2 cameraHelper;
    private boolean selectMultiple;

    public static void launch(Activity activity, String eventName, int showType, boolean isSplit, String cameraType, boolean selectMultiple) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ImageSelectActivity2.class);
        intent.putExtra("showType", showType);
        intent.putExtra("isSplit", isSplit);
        intent.putExtra("eventName", eventName);
        intent.putExtra("selectMultiple", selectMultiple);
        intent.putExtra("cameraType", cameraType);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        initLayout();
        initVar();
    }

    private void initVar() {
        if (getIntent() != null) {
            cameraType = getIntent().getStringExtra("cameraType");
            selectMultiple = getIntent().getBooleanExtra("selectMultiple", false);
            eventName = getIntent().getStringExtra("eventName");
//            isSplit = getIntent().getBooleanExtra("isSplit", isSplit); //获取是否裁剪
            switch (getIntent().getIntExtra("showType", 0)) {      //根据参数显示相册按钮还是显示拍照按钮 默认两个都显示
                case SHOWPIC:
                    tv_take_capture.setVisibility(View.VISIBLE);
                    tv_alumb.setVisibility(View.GONE);
                    break;
                case SHOWALBUM:
                    tv_take_capture.setVisibility(View.GONE);
                    tv_alumb.setVisibility(View.VISIBLE);
                    break;
                default:
                    tv_take_capture.setVisibility(View.VISIBLE);
                    tv_alumb.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            tv_take_capture.setVisibility(View.VISIBLE);
            tv_alumb.setVisibility(View.VISIBLE);
        }
        cameraHelper = new CameraHelper2(this, this, cameraType, selectMultiple);
    }

    protected void initLayout() {
        tv_take_capture = (TextView) findViewById(R.id.tv_take_capture);
        tv_alumb = (TextView) findViewById(R.id.tv_alumb);
        tv_cancle = (TextView) findViewById(R.id.tv_cancle);
        empty_view = findViewById(R.id.empty_view);

        tv_take_capture.setOnClickListener(this);
        tv_alumb.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        empty_view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_take_capture) {
            cameraHelper.startCamera();
        } else if (i == R.id.tv_alumb) {
            cameraHelper.startAlbumDouble();
        } else if (i == R.id.empty_view || i == R.id.tv_cancle) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            //拍照回调
        cameraHelper.onActivityResult(requestCode, resultCode, data);
    }


    //权限申请回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        cameraHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraHelper != null) {
            cameraHelper.clear();
        }
    }


    @Override
    public void onPhotoSuccessful(int requestCode, ArrayList paths) {
        if (TextUtils.isEmpty(eventName)) {
            setResult(Activity.RESULT_OK, new Intent().putExtra(CameraHelper.staticPath, paths).putExtra("type", "Multiple"));
        } else {
            //先只压缩  vin车辆行驶证的图片
            if (eventName.contains("driveLicense")) {
                ArrayList arrayList = cameraHelper.imgCompress(paths);
                uploadDouble(arrayList);
            } else {
                uploadDouble(paths);
            }
        }
        finish();
    }

    @Override
    public void onPhotoFailure(int code, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.show(this, msg);
        }
        finish();
    }

    @Override
    public void noPermissions(int code) {
        ToastUtil.show(this, getString(R.string.no_camera_permission));
    }

    public void uploadDouble(List<String> imgList) {
        finish();
        EventBus.getDefault().post(new EventBean().setTag("showLoading"));
        List<String> netImgUrl = new ArrayList<>();
        new QiNiuHelper(this).uploadListFile(new QiNiuHelper.upLoadListImageListener() {
            @Override
            public void onChange(int index, String url) {
                LogUtil.E("文件选择上传完成index:" + index + " url:" + url);
                netImgUrl.add(url);
                if (index >= imgList.size() - 1) {
                    //如多是单选的话 就赋值到value1上面
                    if (selectMultiple) {
                        EventBus.getDefault().post(new EventBean().setTag(eventName).setValue(netImgUrl));
                        return;
                    }
                    EventBus.getDefault().post(new EventBean().setTag(eventName).setValue(netImgUrl).setValue1(url));
                }

//                EventBus.getDefault().post(new EventBean().setTag("disMissLoading"));
            }

            @Override
            public void onSuccess() {
                EventBus.getDefault().post(new EventBean().setTag("disMissLoading"));
//                finish();
            }

            @Override
            public void onFal(String info) {

            }

            @Override
            public void onError(String info) {
                EventBus.getDefault().post(new EventBean().setTag("disMissLoading"));
//                finish();
            }
        }, (ArrayList<String>) imgList);
    }

    public void upload(String path) {
        EventBus.getDefault().post(new EventBean().setTag("showLoading"));
        new QiNiuHelper(this).uploadSingleFile(new QiNiuHelper.QiNiuCallBack() {
            @Override
            public void onSuccess(String key) {
                EventBus.getDefault().post(new EventBean().setTag("disMissLoading"));
                EventBus.getDefault().post(new EventBean().setTag(eventName).setValue1(key));
                LogUtil.E("单个图片选择上传完成" + key);
            }

            @Override
            public void onFal(String info) {
                EventBus.getDefault().post(new EventBean().setTag("disMissLoading"));
            }
        }, path);
    }
}
