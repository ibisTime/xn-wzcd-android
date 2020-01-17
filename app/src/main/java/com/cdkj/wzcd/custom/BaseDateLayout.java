package com.cdkj.wzcd.custom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.interfaces.BaseDateInterface;
import com.cdkj.wzcd.databinding.LayoutBaseDateBinding;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/28 16:02
 */
public class BaseDateLayout extends LinearLayout {

    private LayoutBaseDateBinding mBinding;
    private Context context;

    private String field;
    private String txtTitle;
    private String txtContent;
    private boolean isRequired;
    private String dateType;

    private String date;
    private String time = "00:00:00";
    private String dateTime;

    private boolean isOnClickEnable = true;

    private BaseDateInterface dateInterface;

    public BaseDateLayout(Context context) {
        this(context, null);
    }

    public BaseDateLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseDateLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseDateLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseDateLayout_title);
        txtContent = typedArray.getString(R.styleable.BaseDateLayout_text);
        isRequired = typedArray.getBoolean(R.styleable.BaseDateLayout_isRequired, false);
        dateType = typedArray.getString(R.styleable.BaseDateLayout_dateType);

        typedArray.recycle();

        init(context);
        setData();

    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_date, this, true);

        if (TextUtils.isEmpty(dateType)) {
            dateType = "0";
        }

        initListener();
    }

    private void setData() {
        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvContent.setHint("请选择" + txtTitle);

        if (!TextUtils.isEmpty(txtContent)) {
            mBinding.tvContent.setText(txtContent);
        }
    }

    public void setText(String text) {
        dateTime = text;
        mBinding.tvContent.setText(text);

    }

    public void setInterface(BaseDateInterface dateInterface) {
        this.dateInterface = dateInterface;
    }

    private void initListener() {
        mBinding.llRoot.setOnClickListener(view -> {

            if (!isOnClickEnable) {
                return;
            }

            showDatePickerDialog(context, 0, Calendar.getInstance(Locale.CHINA));

        });
    }

    /**
     * 日期选择
     *
     * @param context
     * @param themeResId
     * @param calendar
     */
    public void showDatePickerDialog(Context context, int themeResId, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(context, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作

                date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                if (TextUtils.isEmpty(dateType)) {
                    showTimePickerDialog(context, themeResId, calendar);
                } else {

                    if (dateType.equals("0")) {
                        // yyyy-MM-dd HH:mm:ss
                        showTimePickerDialog(context, themeResId, calendar);
                    } else if (dateType.equals("1")) {
                        // yyyy-MM-dd
                        setDate();
                    } else {
                        // yyyy-MM
                        setDate();
                    }

                }

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     *
     * @param context
     * @param themeResId
     * @param calendar
     */
    public void showTimePickerDialog(Context context, int themeResId, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog(context, themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute + ":00";

                        setDate();
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                , true).show();
    }

    private String formatDate() {

        String dateTemp;

        if (!TextUtils.isEmpty(dateType)) {

            if (dateType.equals("0")) {
                // yyyy-MM-dd HH:mm:ss
                dateTemp = date + " " + time;
            } else if (dateType.equals("1")) {
                // yyyy-MM-dd
                dateTemp = date;
            } else {
                // yyyy-MM
                dateTemp = date.split("-")[0] + "-" + date.split("-")[1];
            }

        } else {
            dateTemp = date + " " + time;
        }

        return dateTemp;
    }

    private void setDate() {
        dateTime = formatDate();

        mBinding.tvContent.setText(dateTime);

        if (null != dateInterface) {
            dateInterface.confirm(dateTime);
        }
    }

    public boolean check() {
        if (!isRequired) {
            return false;
        }

        if (TextUtils.isEmpty(dateTime)) {
            ToastUtil.show(context, mBinding.tvContent.getHint().toString());
            return true;
        }

        return false;
    }

    public String getText() {
        return dateTime;
    }

    public Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();
        map.put(field, dateTime);
        return map;
    }

    public void setOnClickEnable(boolean onClickEnable) {
        isOnClickEnable = onClickEnable;
        mBinding.ivMore.setVisibility(onClickEnable ? VISIBLE : GONE);
        mBinding.tvContent.setHint("");
    }

    public void isRequired(boolean isRequired) {
        this.isRequired = isRequired;
        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);
    }
}
