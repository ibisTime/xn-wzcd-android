package com.cdkj.wzcd.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.cdkj.baselibrary.dialog.LoadingDialog;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.view.MyNormalLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/6/6.
 */

public class DatePickerHelper {

    private Context mContext;
    private LoadingDialog loadingDialog;

    private Call call;
    private Calendar startCalendar;
    private Calendar endCalendar;

    public DatePickerHelper build(Context context){
        mContext = context;

        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        return this;
    }

    /**
     * 获取当前时间
     */
    public void getDateRequest(View view, boolean y, boolean m, boolean d, boolean h, boolean mm, boolean s) {

        Map<String, String> map = new HashMap<>();

        call = RetrofitUtils.getBaseAPiService().stringRequest("805126", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<String>(mContext) {
            @Override
            protected void onSuccess(String data, String SucMessage) {
                Date date = DateUtil.parse(data, DateUtil.DEFAULT_DATE_FMT);
                startCalendar.setTime(date);
                endCalendar.setTime(date);
                int year = endCalendar.get(Calendar.YEAR);
                endCalendar.set(Calendar.YEAR, year + 5);
                showDatePicker(view, y, m, d, h, mm, s);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
                clearCall();
            }
        });

    }

    /**
     * 获取当前时间
     */
    public void getDate(View view, boolean y, boolean m, boolean d, boolean h,  boolean mm,  boolean s) {

        Date date = new Date();
        startCalendar.setTime(date);
        endCalendar.setTime(date);
        int year = endCalendar.get(Calendar.YEAR);
        endCalendar.set(Calendar.YEAR, year + 5);
        showDatePicker(view, y, m, d, h, mm, s);
    }


    /**
     * 显示日期picker
     */
    private void showDatePicker(View view, boolean y, boolean m, boolean d, boolean h,  boolean mm,  boolean s) {

        String lable_mins = s ? ":" : "";

        TimePickerView pvTime = new TimePickerView.Builder(mContext, (date, v) -> {//选中事件回调

            if (view instanceof MyNormalLayout){

                if (y & m & d & !h){
                    ((MyNormalLayout) view).setText(DateUtil.format(date, DateUtil.DATE_YMD));
                    view.setTag(DateUtil.format(date, DateUtil.DEFAULT_DATE_FMT));
                }else {
                    ((MyNormalLayout) view).setText(DateUtil.format(date, DateUtil.DATE_MMddHHmm));
                    view.setTag(DateUtil.format(date, DateUtil.DEFAULT_DATE_FMT));
                }

            } else if(view instanceof TextView)  {

                if (y & m & d & !h){
                    ((TextView) view).setText(DateUtil.format(date, DateUtil.DATE_YMD));
                    view.setTag(DateUtil.format(date, DateUtil.DEFAULT_DATE_FMT));
                }else {
                    ((TextView) view).setText(DateUtil.format(date, DateUtil.DATE_MMddHHmm));
                    view.setTag(DateUtil.format(date, DateUtil.DEFAULT_DATE_FMT));
                }


            }

        }).setType(new boolean[]{y, m, d, h, mm, s})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setRange(startCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.YEAR))
                .isCyclic(true)
                .build();

        pvTime.show();
    }

    /**
     * 隐藏Dialog
     */
    private void disMissLoading() {
        if (null != loadingDialog) {
            loadingDialog.closeDialog();
        }
    }

    /**
     * 显示dialog
     */
    private void showLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(mContext);
        }
        if (null != loadingDialog) {
            loadingDialog.showDialog();
        }
    }

    private void clearCall(){
        if (call != null)
            call.cancel();
    }
}
