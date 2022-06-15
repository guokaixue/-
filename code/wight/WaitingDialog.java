package com.abc.code.wight;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.abc.code.R;


public class WaitingDialog extends Dialog {

    private TextView tv_text;

    public WaitingDialog(Context context) {
        super(context, R.style.common_dialog);
        setContentView(R.layout.dialog_loading);
        tv_text = (TextView) findViewById(R.id.tv_text);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     *
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    public WaitingDialog setMessage(String message) {
        tv_text.setText(message);
        return this;
    }
}
