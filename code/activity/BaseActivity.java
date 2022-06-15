package com.abc.code.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.abc.code.MainApplication;
import com.abc.code.R;
import com.abc.code.utils.ActivityManger;
import com.abc.code.wight.StatusBarUtils;
import com.abc.code.wight.WaitingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    public static BaseActivity baseActivity;
    public static List<Activity> activities = new ArrayList<>();
    protected static Application mAppContext = MainApplication.getInstance();
    protected Context mContext = null;
    private Unbinder mUnbinder;
    private WaitingDialog mWaitingDialog;
//    private WaitingDialog mWaitingDialog;

    public static Application getMApplication() {
        return mAppContext;
    }



    public void Exit() {
        this.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }

    protected static void showToast(String text) {
        Toast.makeText(mAppContext, text, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
        ActivityManger.getActivityManger().addActivity(this);
        baseActivity = this;
        addActivity(this);
//        checkPermission();
        mContext = BaseActivity.this;
        try {
            int layoutResID = getContentView(savedInstanceState);
            if (layoutResID != 0) {
                setContentView(layoutResID);
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initData(savedInstanceState);

    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
        }
    }

    /**
     * 设置布局文件
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getContentView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onStop() {
        super.onStop();
        hideInput();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            ActivityManger.getActivityManger().finishActivity(this);
        }
        this.mUnbinder = null;
        super.onDestroy();
    }

    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        baseActivity = this;
        super.onResume();
    }


    /**
     * 显示等待对话框
     */
    public void showWaitingDialog(final String content) {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setMessage(content);
        mWaitingDialog.setCanceledOnTouchOutside(false);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }

    /**
     * 隐藏进度对话框
     */
    public void dismissWaitingDialog() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing() && !isFinishing()) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(baseActivity, "拒绝权部分功能会有问题", Toast.LENGTH_SHORT).show();
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
        }
    }
}
