package com.abc.code.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.abc.code.R;
import com.abc.code.dao.DaoHelper;
import com.abc.code.dao.UserBean;
import com.abc.code.wight.CustomTitleBar;

import butterknife.BindView;


public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_name)
    public EditText et_name;
    @BindView(R.id.btn_register)
    public Button btn_register;
    @BindView(R.id.et_pwd)
    public EditText et_pwd;
    @BindView(R.id.customTv)
    CustomTitleBar customTv;


    @Override
    protected int getContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register_expert;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        initData();
    }


    private void initData() {

        customTv.setOnTitleClickListener(new CustomTitleBar.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                Exit();
            }

            @Override
            public void onRightClick() {

            }
        });

        btn_register.setOnClickListener(view -> {
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

            register(userName, passWord);
        });
    }



    public void register(String account, String pwd) {

    //验证用户名密码非空
    if (TextUtils.isEmpty(account)) {
        showToast("用户名不能为空");
        return;
    }

    if (TextUtils.isEmpty(pwd)) {
        showToast("密码不能为空");
        return;
    }

    UserBean userBean = new UserBean();
    userBean.setPassWord(pwd);
    userBean.setUserName(account);
    DaoHelper.getInstance(this).registerUser(userBean);
    showToast("注册成功");
    finish();
   }

}
