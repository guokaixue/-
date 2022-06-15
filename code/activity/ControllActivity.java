package com.abc.code.activity;

import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abc.code.R;
import com.abc.code.wight.CustomTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: hedianbo.
 * @Contanct: 1040080642@qq.com
 * @date: 2020/5/12 11:46 AM.
 * @desc:
 */
public class ControllActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    CustomTitleBar tvTitle;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.switch2)
    Switch switch2;
    @BindView(R.id.switch3)
    Switch switch3;

    @Override
    protected int getContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_controll;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);

        tvTitle.setOnTitleClickListener(new CustomTitleBar.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                Exit();
            }

            @Override
            public void onRightClick() {

            }
        });
    }
}
