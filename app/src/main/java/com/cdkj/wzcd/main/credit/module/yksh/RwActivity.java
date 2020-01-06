package com.cdkj.wzcd.main.credit.module.yksh;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActYkshBinding;
import com.cdkj.wzcd.databinding.ActYkshRwBinding;
import com.cdkj.wzcd.main.credit.module.yksh.bean.RwBean;
import com.cdkj.wzcd.model.SalesmanModel;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2020/1/1 22:23
 */
public class RwActivity extends AbsBaseLoadActivity {

    private ActYkshRwBinding mBinding;

    private RwBean bean;

    private List<DataDictionary> userList = new ArrayList<>();

    public static void open(Context context, RwBean bean) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RwActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_yksh_rw, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        init();
        initListener();

        getUserList();
    }

    private void init() {
        bean = (RwBean) getIntent().getSerializableExtra(CdRouteHelper.DATA_SIGN);

        if (null == bean) {
            mBaseBinding.titleView.setRightTitle("新增");
        } else {
            mBaseBinding.titleView.setRightTitle("修改");

            setView();
        }
    }

    private void setView() {

        mBinding.elName.setText(bean.getName());
        mBinding.elTime.setText(bean.getTime());
        mBinding.slUser.setTextAndKey(bean.getGetUser(), bean.getGetUserName());

    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            finish();
        });

        mBinding.btnApply.setOnClickListener(view -> {
            if (check()) {

                RwBean rwBean = setBean();

                if (null == bean) {
                    EventBus.getDefault()
                            .post(new EventBean().setTag("yksh_rw_add").setValue(rwBean));
                } else {
                    EventBus.getDefault()
                            .post(new EventBean().setTag("yksh_rw_modify").setValue(rwBean));
                }

                finish();

            }
        });
    }

    private boolean check() {
        if (mBinding.elName.check()) {
            return false;
        }

        if (mBinding.elTime.check()) {
            return false;
        }

        if (mBinding.slUser.check()) {
            return false;
        }

        return true;
    }

    private RwBean setBean() {

        RwBean rwBean = new RwBean();

        rwBean.setName(mBinding.elName.getText());
        rwBean.setTime(mBinding.elTime.getText());
        rwBean.setGetUser(mBinding.slUser.getDataKey());
        rwBean.setGetUserName(mBinding.slUser.getDataValue());

        return rwBean;
    }

    public void getUserList() {

        Map<String, String> map = new HashMap<>();

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getSalesmanList("630066", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseListCallBack<SalesmanModel>(this) {
            @Override
            protected void onSuccess(List<SalesmanModel> data, String SucMessage) {

                userList.clear();
                for (SalesmanModel model : data) {

                    userList.add(new DataDictionary().setDkey(model.getUserId())
                            .setDvalue(model.getRealName()));

                }
                mBinding.slUser.setData(userList);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
