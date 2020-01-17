package com.cdkj.baselibrary.activitys;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.base.BaseActivity;
import com.cdkj.baselibrary.interfaces.CameraPhotoListener;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.baselibrary.utils.glidetransforms.ImageEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.PathUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 打开相机 相册 图片裁剪 功能
 */
public class ImageSelectActivity extends BaseActivity implements View.OnClickListener,
        CameraPhotoListener {

    private String eventName;
    private String cameraType;

    private TextView tv_take_capture;// 拍照
    private TextView tv_alumb;// 相册选取
    private TextView tv_cancle;// 取消
    private View empty_view;// 取消

    private boolean isSplit = false;//执行相机或拍照后是否需要裁剪 默认不需要

    public static final int SHOWPIC = 1; //显示拍照按钮
    public static final int SHOWALBUM = 2;//显示相册

    private CameraHelper cameraHelper;
    private boolean selectMultiple;
    private final int REQUEST_CODE_ZHIHU = 699;

    /**
     * @param activity
     * @param photoid
     * @param showType 显示的按钮
     * @param isSplit  是否裁剪
     */
    public static void launch(Activity activity, int photoid, int showType, boolean isSplit) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ImageSelectActivity.class);
        intent.putExtra("showType", showType);
        intent.putExtra("isSplit", isSplit);
        activity.startActivityForResult(intent, photoid);
    }

    public static void launch(Activity activity, int requestCode, boolean isSplit) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ImageSelectActivity.class);

        activity.startActivityForResult(intent, requestCode);
    }

    public static void launch(Activity activity, String eventName, int showType, boolean isSplit, String cameraType) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ImageSelectActivity.class);
        intent.putExtra("showType", showType);
        intent.putExtra("isSplit", isSplit);
        intent.putExtra("eventName", eventName);
        intent.putExtra("cameraType", cameraType);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, String eventName, int showType, boolean isSplit, String cameraType, boolean selectMultiple) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ImageSelectActivity.class);
        intent.putExtra("showType", showType);
        intent.putExtra("isSplit", isSplit);
        intent.putExtra("eventName", eventName);
        intent.putExtra("selectMultiple", selectMultiple);
        intent.putExtra("cameraType", cameraType);
        activity.startActivity(intent);
    }

    public static void launchFragment(Fragment fragment, int photoid) {
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        Intent intent = new Intent(fragment.getActivity(), ImageSelectActivity.class);
        fragment.startActivityForResult(intent, photoid);
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
            isSplit = getIntent().getBooleanExtra("isSplit", isSplit); //获取是否裁剪
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
        cameraHelper = new CameraHelper(this, this, cameraType, selectMultiple);
        cameraHelper.setSplit(isSplit);
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
            if (selectMultiple) {
                startAlbumDouble();
            } else {
                cameraHelper.startAlbum();
            }
        } else if (i == R.id.empty_view || i == R.id.tv_cancle) {
            finish();
        }
    }

    /**
     * 知乎三方选择图片框架
     */
    private void startAlbumDouble() {

        Matisse.from(this)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)

                //是否拍照  注意fileprovider 必须设置并且配置   第三个参数是设置保存的文件夹
                .captureStrategy(new CaptureStrategy(true, "com.cdkj.wzcd_b.provider"))
                .maxSelectable(9)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new ImageEngine())
                .setOnSelectedListener((uriList, pathList) -> {
                    Log.e("onSelected", "onSelected: pathList=" + pathList);
                })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> {
                    Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                })
                .forResult(REQUEST_CODE_ZHIHU);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ZHIHU && data != null) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            List<String> imagePath = new ArrayList<>();
            for (Uri uri : mSelected) {
                String path;
                try {
                    path = PathUtils.getPath(this, uri);
                } catch (Exception ex) {
                    path = getRealFilePath(this, uri);
                }
                imagePath.add(path);
            }
            uploadDouble(imagePath);

        } else {
//            //拍照回调
            cameraHelper.onActivityResult(requestCode, resultCode, data);
        }

    }

    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
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
    public void onPhotoSuccessful(int code, String path) {
        if (TextUtils.isEmpty(eventName)) {
            setResult(Activity.RESULT_OK, new Intent().putExtra(CameraHelper.staticPath, path));
        } else {
            upload(path);
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
        new QiNiuHelper(this).upLoadListPic(imgList, new QiNiuHelper.upLoadListImageListener() {
            @Override
            public void onChange(int index, String url) {
                netImgUrl.add(url);
                if (index >= imgList.size() - 1) {
                    EventBus.getDefault().post(new EventBean().setTag(eventName).setValue(netImgUrl));
                }

                LogUtil.E("多个图片选择上传完成index:" + index + " url:" + url);
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
        });
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
