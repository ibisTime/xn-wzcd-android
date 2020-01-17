package com.cdkj.baselibrary.utils.glidetransforms;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

/**
 * @updateDts 2020/1/6
 */
public class ImageEngine implements com.zhihu.matisse.engine.ImageEngine {
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)//这里可自己添加占位图
                .override(resize, resize);
        Glide.with(context)
                .asBitmap()  // some .jpeg files are actually gif
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)//这里可自己添加占位图
                .override(resize, resize);
        Glide.with(context)
                .asGif()  // some .jpeg files are actually gif
                .load(uri)
                .apply(options)
                .into(imageView);
    }


    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(resizeX, resizeY);
        Glide.with(context)
                .asGif()  // some .jpeg files are actually gif
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
