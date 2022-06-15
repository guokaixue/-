package com.abc.code.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.abc.code.R;
import com.abc.code.bean.KongQi;
import com.abc.code.wight.CustomMeterViewCo2;
import com.abc.code.wight.CustomMeterViewKongQi;
import com.abc.code.wight.CustomMeterViewTmeper;
import com.abc.code.wight.CustomTitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @author: hedianbo.
 * @Contanct: 1040080642@qq.com
 * @date: 2020/5/9 1:46 PM.
 * @desc: 数据展示页面
 */
public class ShowDataActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    CustomTitleBar tv_title;
    @BindView(R.id.clockTemperature)
    CustomMeterViewTmeper clockTemperature;
    @BindView(R.id.clockKongQi)
    CustomMeterViewKongQi clockKongQi;
    @BindView(R.id.clockCo2)
    CustomMeterViewCo2 clockCo2;
    private MyHandler myHandler;


    @Override
    protected int getContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_show_data;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        tv_title.setOnTitleClickListener(new CustomTitleBar.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                Exit();
            }

            @Override
            public void onRightClick() {

            }
        });
        startHandler();

        getkongshiData();
        getTemperData();
        getCo2Data();
    }

    long delayTime = 1000 * 30;//默认从传感器获取数据的频率是30秒

    private void startHandler() {
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(100, delayTime);//延迟30秒后去从传感器获取数据
    }

    /**
     * Handler定时器 调整获取数据的频率
     */
    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        private final WeakReference<ShowDataActivity> mAct;

        public MyHandler(ShowDataActivity mainActivity) {
            mAct = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ShowDataActivity mainAct = mAct.get();
            super.handleMessage(msg);
            if (mainAct != null && myHandler != null) {
                if (msg.what == 100) {
                    getkongshiData();
                    getTemperData();
                    getCo2Data();
                }
            }
        }
    }

    private void releaseHandler() {
        if (myHandler != null) {
            myHandler.removeMessages(100);
            myHandler = null;
        }
    }


    private void getkongshiData() {
        showWaitingDialog("获取数据");
        OkGo.<String>get("http://api.heclouds.com/devices/593764796/datastreams/kongshi")
                .headers("api-key","Y2xPRtenFNWfQobgGcKBqwkeumA=")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissWaitingDialog();
                        Gson gson = new Gson();
                        KongQi kongQi = gson.fromJson(response.body(), new TypeToken<KongQi>() {
                        }.getType());
                        if (kongQi.getErrno() == 0) {
                            clockKongQi.setPercentData(20,new DecelerateInterpolator());
                        } else {
                            Log.e("hedb", "onSuccess: "+kongQi.getError() );
                            showToast(kongQi.getError());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissWaitingDialog();
                        showToast("服务器异常");
                    }
                });


    }

    private void getTemperData() {
        OkGo.<String>get("http://api.heclouds.com/devices/593764796/datastreams/timp")
                .headers("api-key","Y2xPRtenFNWfQobgGcKBqwkeumA=")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        KongQi kongQi = gson.fromJson(response.body(), new TypeToken<KongQi>() {
                        }.getType());
                        if (kongQi.getErrno() == 0) {
                            clockTemperature.setPercentData(kongQi.getData().getCurrent_value(),new DecelerateInterpolator());

                        } else {
                            Log.e("hedb", "onSuccess: "+kongQi.getError() );
                            showToast(kongQi.getError());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast("服务器异常");
                    }
                });

    }

    private void getCo2Data() {
        OkGo.<String>get("http://api.heclouds.com/devices/593764796/datastreams/co2")
                .headers("api-key","Y2xPRtenFNWfQobgGcKBqwkeumA=")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        KongQi kongQi = gson.fromJson(response.body(), new TypeToken<KongQi>() {
                        }.getType());
                        if (kongQi.getErrno() == 0) {
                            clockCo2.setPercentData(kongQi.getData().getCurrent_value(),new DecelerateInterpolator());

                        } else {
                            Log.e("hedb", "onSuccess: "+kongQi.getError() );
                            showToast(kongQi.getError());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast("服务器异常");
                    }
                });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseHandler();
    }
}
