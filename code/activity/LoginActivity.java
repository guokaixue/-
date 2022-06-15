package com.abc.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.abc.code.R;
import com.abc.code.dao.DaoHelper;
import com.abc.code.dao.UserBean;
import com.abc.code.utils.SpUtil;

import butterknife.BindView;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_name)
    public EditText et_name;
    @BindView(R.id.btn_go_register)
    public Button btn_go_register;
    @BindView(R.id.et_pwd)
    public EditText et_pwd;
    @BindView(R.id.btn_login)
    public Button bt_login;


    @Override
    protected int getContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        initData();
    }


    private void initData() {

        if (SpUtil.getBoolean("login")) {
            Intent intent = new Intent(LoginActivity.this, MainUserActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        bt_login.setOnClickListener(view -> {
            String userName = et_name.getText().toString().trim();
            if (TextUtils.isEmpty(userName)) {
                showToast("用户名不能为空");
                return;
            }

            String passWord = et_pwd.getText().toString().trim();
            if (TextUtils.isEmpty(passWord)) {
                showToast("密码不能为空");
                return;
            }

            login(userName, passWord);
        });

        btn_go_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }


    public void login(String account, String pwd) {

        //验证用户名密码非空
        if (TextUtils.isEmpty(account)) {
            showToast("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToast("密码不能为空");
            return;
        }

        UserBean userBean = DaoHelper.getInstance(this).queryUser(account, pwd);
        if (userBean == null) {
            showToast("用户名或者密码错误");
        } else {
            SpUtil.setBoolean("login", true);
            SpUtil.setData("userId", userBean.getId() + "");
            SpUtil.setData("userName", userBean.getUserName());
            SpUtil.setData("passWord", userBean.getPassWord());

            startActivity(MainUserActivity.class);
            finish();
        }

    }
}
