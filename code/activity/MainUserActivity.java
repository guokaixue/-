package com.abc.code.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.abc.code.R;
import com.abc.code.utils.ShowDialogManager;
import com.abc.code.utils.SpUtil;
import com.abc.code.wight.CustomTitleBar;

import butterknife.BindView;

public class MainUserActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    CustomTitleBar tvTitle;
    @BindView(R.id.controller)
    TextView controller;
    @BindView(R.id.getData)
    TextView getData;


    @Override
    protected int getContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main_user;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        tvTitle.setOnTitleClickListener(new CustomTitleBar.OnTitleClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                ShowDialogManager.getInstance().showDialogHint(MainUserActivity.this, "确定退出登录?", view -> {
                    startActivity(LoginActivity.class);
                    SpUtil.setBoolean("login",false);
                    showToast("退成登录成功");
                    finish();
                });
            }
        });

        getData.setOnClickListener(view -> startActivity(ShowDataActivity.class));

        controller.setOnClickListener(view -> startActivity(ControllActivity.class));

    }


}
