package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.wzcd.R;

/**
 * 首页正方形行Layout
 * Created by cdkj on 2018/5/28.
 */

public class MySquareRowLayout extends LinearLayout {

    private Context context;

    private TextView tvContent;
    private ImageView ivContent;
    private TextView tvRedPoint;

    private int imgContentId;
    private String txtContent;

    public MySquareRowLayout(Context context) {
        this(context, null);
    }

    public MySquareRowLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySquareRowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySquareRowLayout, 0, 0);
        txtContent = typedArray.getString(R.styleable.MySquareRowLayout_txt_content);
        imgContentId = typedArray.getResourceId(R.styleable.MySquareRowLayout_img_content, R.drawable.default_pic);

        typedArray.recycle();

        init(context);

        setData();
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_my_square_row, this, true);

        tvContent = findViewById(R.id.tv_content);
        ivContent = findViewById(R.id.iv_content);
        tvRedPoint = findViewById(R.id.tv_red_point);
    }

    private void setData() {
        tvContent.setText(txtContent);
        ivContent.setImageResource(imgContentId);
    }

    /**
     * 设置红点数量并显示
     * @param count
     */
    public void setPointCount(int count){

        tvRedPoint.setText(count+"");
        if (count == 0){
            tvRedPoint.setVisibility(GONE);
        }else {
            tvRedPoint.setVisibility(VISIBLE);
        }
    }



}
