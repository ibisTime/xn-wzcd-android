package com.cdkj.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.cdkj.baselibrary.CdApplication;
import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.interfaces.CameraPhotoListener2;
import com.cdkj.baselibrary.utils.glidetransforms.ImageEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.PathUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import top.zibin.luban.Luban;

import static android.provider.MediaStore.ACTION_VIDEO_CAPTURE;

/**
 * 拍照、相册辅助类
 * 使用注意 需要在启动页面中调用onActivityResult处理拍照回调
 * 调用onRequestPermissionsResult处理权限回调
 * 调用clear方法防止内存泄漏
 * Created by cdkj on 2017/11/7.
 */
//由CameraHelper2 修改而来 通过反射打开Activity从而兼容Fragment和Activity
//TODO 裁剪接口抽取
public class CameraHelper2 {

    public final static String CAMERA_TYPE_IMAGE = "0";
    public final static String CAMERA_TYPE_VIDEO = "1";

    public final static int CAPTURE_PHOTO_CODE = 3;//相机
    public final static int CAPTURE_WALBUM_CODE = 4;//相册

    private final boolean selectMultiple;

    private int mRequestCode = -1;//用于记录是相机还是相册裁剪

    private Activity mContext;
    private String cameraType = CAMERA_TYPE_IMAGE;

    private Uri imageUrl;
    protected CompositeDisposable mSubscription;
    private PermissionHelper mPreHelper;//权限请求

    public final static String staticPath = "imgSelect";
    public final static String cropPath = "cropPath";
    private String photoPath;//拍照图片路径
//    private boolean isSplit = false;//执行相机或拍照后是否需要裁剪 默认裁剪

    private CameraPhotoListener2 mCameraPhotoListener;

    private CamerahelperCropInterface mCamerahelperCropInterface;//裁剪接口

    //需要的权限
    private String[] needLocationPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    /**
     * 默认启动裁剪
     *
     * @param cameraPhotoListener 获取图片监听
     */
    public CameraHelper2(@NonNull Activity object, @NonNull CameraPhotoListener2 cameraPhotoListener, String cameraType, boolean selectMultiple) {
        this.mContext = object;
        this.cameraType = cameraType;
        checkCallingObjectSuitability(object);
//        this.isSplit = true;
        this.selectMultiple = selectMultiple;
        this.mCameraPhotoListener = cameraPhotoListener;
        mSubscription = new CompositeDisposable();
        mPreHelper = new PermissionHelper(object);
        mCamerahelperCropInterface = new defaultCropInterface();

    }

    /**
     * 判断权限并启动相册
     * 通过三方启动可以多选
     *
     * @return
     */
    public void startAlbumDouble() {
        if (isNeedRequestPremission()) {
            requestPermissions(CAPTURE_WALBUM_CODE);
            return;
        }
        startImageFromAlbumZhihu();
    }


    /**
     * 判断权限并启动相机
     *
     * @return
     */
    public void startCamera() {
        if (isNeedRequestPremission()) {
            requestPermissions(CAPTURE_PHOTO_CODE);
            return;
        }
        startImageFromCamera();
    }


    /**
     * 请求权限
     *
     * @param type 判断是相册还是相机
     */
    private void requestPermissions(final int type) {
        mRequestCode = type;
        mPreHelper.requestPermissions(new PermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {
                switch (type) {
                    case CAPTURE_PHOTO_CODE:
                        startImageFromCamera();
                        break;
                    case CAPTURE_WALBUM_CODE:
                        startImageFromAlbumZhihu();
                        break;
                }
            }

            @Override
            public void doAfterDenied(String... permission) {
                mCameraPhotoListener.noPermissions(type);
            }
        }, needLocationPermissions);
    }


    /**
     * 判断是否存在可用相机
     *
     * @return
     */
    public boolean hasCamera() {
        if (mContext == null) {
            return false;
        }
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    // 调相机拍照
    private void startImageFromCamera() {

        mRequestCode = CAPTURE_PHOTO_CODE;

        if (!hasCamera())  //判断有没有可用相机
        {
            mCameraPhotoListener.onPhotoFailure(CAPTURE_PHOTO_CODE,
                    CdApplication.getContext().getString(R.string.no_camera));
            return;
        }

        if (FileUtils.isExternalStorageWritable()) {
            Intent intent;
            if (CAMERA_TYPE_IMAGE.equals(cameraType)) {
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
            } else {
                intent = new Intent(ACTION_VIDEO_CAPTURE);
            }

            File file = FileUtils.saveAlbumPic("cream");
            imageUrl = FileProviderHelper.getUriForFile(mContext, file);
            photoPath = file.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivity(intent, CAPTURE_PHOTO_CODE);
        } else {
            mCameraPhotoListener.onPhotoFailure(CAPTURE_PHOTO_CODE,
                    CdApplication.getContext().getString(R.string.no_sd_card));
        }
    }

    /**
     * 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            mCameraPhotoListener.onPhotoFailure(requestCode, "");
            return;
        }

        switch (requestCode) {
            case CAPTURE_PHOTO_CODE:// 拍照
                mRequestCode = CAPTURE_PHOTO_CODE;
                cameraNext();
                break;
            case CAPTURE_WALBUM_CODE:
                abumNextZhihu(data);
                break;
            default:
                break;
        }
    }

    //修复知乎  再拍照的时候获取不到图片的路径的bug
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

    /**
     * 相机
     */
    private void cameraNext() {
//        if (isSplit) {
//            if (!isNeedUriAdapte()) {
//                startCrop(imageUrl.getPath());
//            } else {
//                startCrop(photoPath);
//            }
//        } else {
        try {
            if (!CameraHelper.isNeedUriAdapte()) {
                File file = new File(imageUrl.getPath());
                ArrayList arrayList = new ArrayList();
                arrayList.add(imageUrl.getPath());
                mCameraPhotoListener.onPhotoSuccessful(CAPTURE_PHOTO_CODE, arrayList);
                //通知相册更新
                CdApplication.getContext()
                        .sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.fromFile(file)));

            } else {
                File file = new File(photoPath);
                ArrayList arrayList = new ArrayList();
                arrayList.add(photoPath);
                mCameraPhotoListener.onPhotoSuccessful(CAPTURE_PHOTO_CODE, arrayList);
                //通知相册更新
                CdApplication.getContext()
                        .sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.fromFile(file)));

            }

        } catch (Exception e) {
            mCameraPhotoListener.onPhotoFailure(CAPTURE_PHOTO_CODE, "图片获取失败");
        }
    }
//    }

    /**
     * 处理知乎选择返回的数据
     */
    private void abumNextZhihu(Intent data) {

        List<Uri> mSelected = Matisse.obtainResult(data);
        ArrayList<String> imagePath = new ArrayList<>();
        //通过url获取文件(图片/视频)路径  但是用框架的
        //PathUtils.getPath在获取拍照路径的时候会报错 下面自定义了一个获取的方法 修复此bug
        for (Uri uri : mSelected) {
            String path;
            try {
                path = PathUtils.getPath(mContext, uri);
            } catch (Exception ex) {
                path = getRealFilePath(mContext, uri);
            }
            imagePath.add(path);
        }

//        imgCompress(imagePath);

//        driveLicense
        mCameraPhotoListener.onPhotoSuccessful(CAPTURE_WALBUM_CODE, imagePath);
    }

    /**
     * 图片压缩
     */
    public ArrayList<String> imgCompress(ArrayList<String> imagePaths) {
        //
        ArrayList<String> compressNewsPaths = new ArrayList<>();
        try {

            Flowable.just(imagePaths)
//                    .observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<List<String>, List<File>>() {
                        @Override
                        public List<File> apply(@NonNull List<String> list) throws Exception {
                            // 同步方法直接返回压缩后的文件

                            List<File> filesList = Luban.with(mContext).load(list).get();
                            for (File file : filesList) {
                                compressNewsPaths.add(file.getAbsolutePath());
                            }
                            return filesList;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<File>>() {
//                    @Override
//                    public void accept(List<File> files) throws Exception {
//
//                    }
//                })
                    .subscribe();


        } catch (Exception e) {
            e.printStackTrace();

        }

        return compressNewsPaths;
        //下面是异步的方法
//        try {
//            Luban.with(mContext)
//                    .load(imagePaths)
//                    .ignoreBy(100)
//                    .setTargetDir(FileUtils.getAlbumStorageDir("aaaaa").getAbsolutePath())
//                    .filter(new CompressionPredicate() {
//                        @Override
//                        public boolean apply(String path) {
//                            LogUtil.E("pppppp原始地址:" + path);
//                            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
//                        }
//                    })
//                    .setCompressListener(new OnCompressListener() {
//                        @Override
//                        public void onStart() {
//                            // TODO 压缩开始前调用，可以在方法内启动 loading UI
//                            LogUtil.E("pppppp开始压缩");
//                        }
//
//                        @Override
//                        public void onSuccess(File file) {
//                            // TODO 压缩成功后调用，返回压缩后的图片文件
//                            long kb = file.length() / 1000;
//
//                            LogUtil.E("pppppp压缩成功:大小为:" + kb + "kb 路径为:" + file.getAbsolutePath());
//                            compressNewsPaths.add(file.getAbsolutePath());
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                            // TODO 当压缩过程出现问题时调用
//                            LogUtil.E("pppppp压缩失败:" + e.getMessage());
//                        }
//                    })
//                    .launch();
//        } catch (Exception ex) {
//            UITipDialog.showFail(mContext, "图片格式不支持");
//        }
//        return compressNewsPaths;
    }


    /**
     * 启动裁剪页面
     *
     * @param path
     */
    public void startCrop(String path) {
        mCamerahelperCropInterface.startCrop(mContext, mRequestCode, path);
    }

    // 调知乎的图片选择
    private void startImageFromAlbumZhihu() {
        Set<MimeType> mimeTypes = TextUtils.equals(CAMERA_TYPE_IMAGE, cameraType) ? MimeType.ofImage() : MimeType.ofVideo();
        Matisse.from(mContext)
                .choose(mimeTypes, false)
                .countable(true)
                .capture(true)
                //是否拍照  注意fileprovider 必须设置并且配置   第三个参数是设置保存的文件夹
                .captureStrategy(new CaptureStrategy(true, "com.cdkj.wzcd_b.provider"))
                .maxSelectable(selectMultiple ? 9 : 1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))  //自定义拦截器
                .gridExpectedSize(mContext.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new ImageEngine())//自定义图片加载器  支持gilde和picos
                .setOnSelectedListener((uriList, pathList) -> {
                    Log.e("onSelected", "onSelected: pathList=" + pathList);
                })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(1)//原图尺寸
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> {
                    Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                })
                .forResult(CAPTURE_WALBUM_CODE);
    }


    /**
     * 获取权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode,
                                           @android.support.annotation.NonNull String[] permissions,
                                           @android.support.annotation.NonNull int[] grantResults) {
        if (mPreHelper == null) {
            return;
        }
        mPreHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * 清除
     */
    public void clear() {
        if (mSubscription != null) {
            mSubscription.dispose();
            mSubscription.clear();
        }
        this.mContext = null;
    }

    /**
     * 裁剪接口
     */
    public interface CamerahelperCropInterface {

        void startCrop(Object context, int requestCode, String path);//从Activity页面启动
    }


    /**
     * 默认裁剪实现
     */
    class defaultCropInterface implements CamerahelperCropInterface {

        @Override
        public void startCrop(Object context, int requestCode, String path) {

//            startActivity(intent, CAPTURE_ZOOM_CODE);

//            final Uri selectedUri = intent.getData();
//            UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getCacheDir(), "SampleCropImage")));
//
//            uCrop.withAspectRatio(16, 9)
//                    .start(this);

        }
    }


    /**
     * 检查传递Context是否合法
     *
     * @param object
     */
    private void checkCallingObjectSuitability(@Nullable Object object) {
        if (object == null) {
            throw new IllegalArgumentException("camera start object is null");
        }

        boolean isActivity = object instanceof android.app.Activity;
        boolean isSupportFragment = object instanceof android.support.v4.app.Fragment;
        boolean isAppFragment = object instanceof android.app.Fragment;
        if (!(isSupportFragment || isActivity || (isAppFragment && CameraHelper
                .isNeedRequestPremission()))) {
            if (isAppFragment) {
                throw new IllegalArgumentException(
                        "Target SDK needs to be greater than 23 if caller is android.app.Fragment");
            } else {
                throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
            }
        }
    }

    /**
     * 是否需要进行权限申请
     *
     * @return
     */
    public static boolean isNeedRequestPremission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否需要7.0适配
     *
     * @return false 不需要
     */
    public static boolean isNeedUriAdapte() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * 通过反射调用startActivityForResult打开activity
     *
     * @param intent
     * @param requestCode
     */

    public void startActivity(Intent intent, int requestCode) {
        try {
            Method method = this.mContext.getClass()
                    .getMethod("startActivityForResult", new Class[]{Intent.class, Integer.TYPE});
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(this.mContext, new Object[]{intent, requestCode});
        } catch (Exception var2) {
            var2.printStackTrace();
            ToastUtil.show(mContext,
                    CdApplication.getContext().getString(R.string.error_unknown));
        }
    }


}
