package com.abc.code.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.code.R;


public class CustomDialog extends Dialog {

    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;
    private int gravity = Gravity.CENTER;

    private CustomDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        gravity = builder.gravity;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }


    private CustomDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        gravity = builder.gravity;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = gravity;
        if (height != 0) {
            lp.height = height;
        }

        if (width != 0) {
            lp.width = width;
        } else {
            lp.width = context.getResources().getDisplayMetrics().widthPixels;
        }
        win.setAttributes(lp);
    }

    public static class Builder {

        public EditText userName;
        public EditText oldPass;
        public EditText newPass;
        private Context context;
        private int height, width, gravity;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            if (resView == R.layout.item_dialog_username) {
                userName = view.findViewById(R.id.tv_content);
            }

            if (resView == R.layout.item_dialog_pass) {
                oldPass = view.findViewById(R.id.old_pass);
                newPass = view.findViewById(R.id.new_pass);
            }

            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }

        public Builder heightdp(int val) {
            height = TUtil.dp2px(context, val);
            return this;
        }

        public Builder widthdp(int val) {
            width = TUtil.dp2px(context, val);
            return this;
        }

        public Builder gravity(int val) {
            gravity = val;
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public Builder addTextContent(String text) {
            if (userName != null) {
                userName.setHint(text);
            }

            return this;
        }

        public Builder addImage(int viewRes, Bitmap bitmap) {
            View image = view.findViewById(viewRes);
            if (image instanceof ImageView) {
                ((ImageView) image).setImageBitmap(bitmap);
            }

            return this;
        }


        public Builder addText(int viewRes, String text) {
            View textv = view.findViewById(viewRes);
            if (textv instanceof TextView) {
                ((TextView) textv).setText(text);
            }

            return this;
        }

        public Builder addImageView(int viewRes, int resource) {
            View image = view.findViewById(viewRes);
            if (image instanceof ImageView) {
                if (resource != 0) {
                    ((ImageView) image).setImageResource(resource);
                }
            }
            return this;
        }

        /**
         * 是否显示 取消的叉  默认显示
         *
         * @param viewRes
         * @param b
         * @return
         */
        public Builder isShowCancel(int viewRes, boolean b) {
            if (!b) {
                View image = view.findViewById(viewRes);
                if (image instanceof ImageView) {
                    image.setVisibility(View.GONE);
                }
            }
            return this;
        }

        public CustomDialog build() {
            if (resStyle != -1) {
                return new CustomDialog(this, resStyle);
            } else {
                return new CustomDialog(this);
            }
        }
    }
}
