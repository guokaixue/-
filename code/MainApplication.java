package com.abc.code;

import android.app.Application;

import com.abc.code.dao.DatabaseHelper;
import com.abc.code.utils.SpUtil;

public class MainApplication extends Application {

    private static MainApplication mApplication;

    public static synchronized MainApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        SpUtil.init(this);
        DatabaseHelper.getInstance(this);
    }

}
