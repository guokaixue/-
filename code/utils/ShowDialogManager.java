package com.abc.code.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.abc.code.R;

public class ShowDialogManager {

	private static ShowDialogManager showDialogManager;
	private CustomDialog dialog;
	private CustomDialog customDialog;

	public interface AddOnClikLister {
		void setOnclickLister(View view);
	}


    public interface AddOnClikListerUser {
        void setOnclickLister(String userName);
    }

    public interface AddOnClikListerPass {
        void setOnclickLister(String oldPass, String newPass);
    }

	private ShowDialogManager() {
	}

	public static ShowDialogManager getInstance() {
		if (showDialogManager == null) {
			synchronized (ShowDialogManager.class) {
				if (showDialogManager == null) {
					showDialogManager = new ShowDialogManager();
				}
			}
		}
		return showDialogManager;
	}


	public void showDialogHint(Context context, String tv, AddOnClikLister listener) {
		CustomDialog.Builder builder = new CustomDialog.Builder(context);
		dialog = builder
				.style(R.style.DialogOne)
				.heightdp(180)
				.widthdp(280)
				.cancelTouchout(false)
				.view(R.layout.item_dialog_hint)
				.addText(R.id.tv_content, tv)

				.addViewOnclick(R.id.tv_cancel_dialog, v -> {
					if (dialog != null && dialog.isShowing()) {
						dialog.dismiss();
					}
				})
				.addViewOnclick(R.id.tv_sure_del, v -> {
					if (dialog != null && dialog.isShowing()) {
						dialog.dismiss();
					}
					if (listener != null) {
						listener.setOnclickLister(v);
					}
				})
				.build();
		dialog.show();
	}


    public void showDialogImage(Context context, Bitmap tv) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        dialog = builder
                .style(R.style.DialogOne)
                .heightdp(280)
                .widthdp(280)
                .cancelTouchout(true)
                .view(R.layout.item_dialog_image)
                .addImage(R.id.codeImage, tv)
                .build();
        dialog.show();
    }

    public void showUpdatePassWord(Context context, AddOnClikListerPass listener) {

        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        dialog = builder
                .style(R.style.DialogOne)
                .heightdp(230)
                .widthdp(280)
                .cancelTouchout(false)
                .view(R.layout.item_dialog_pass)
                .addViewOnclick(R.id.tv_cancel_dialog, v -> {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                })
                .addViewOnclick(R.id.tv_sure_del, v -> {
                    if (listener != null) {
                        String oldPass = builder.oldPass.getText().toString();
                        String newPass = builder.newPass.getText().toString();

                        if (TextUtils.isEmpty(oldPass)){
                            Toast.makeText(context, "旧密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(newPass)){
                            Toast.makeText(context, "新密码密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        listener.setOnclickLister(oldPass,newPass);
                    }

                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
    }


    public void showUpdateUser(Context context, String tv,String tishi,AddOnClikListerUser listener) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        dialog = builder
                .style(R.style.DialogOne)
                .heightdp(180)
                .widthdp(280)
                .cancelTouchout(false)
                .view(R.layout.item_dialog_username)
                .addTextContent(tv)
                .addViewOnclick(R.id.tv_cancel_dialog, v -> {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                })
                .addViewOnclick(R.id.tv_sure_del, v -> {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (listener != null) {
                        String toString = builder.userName.getText().toString();
                        if (TextUtils.isEmpty(toString)){
                            Toast.makeText(context, tishi, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        listener.setOnclickLister(toString);
                    }
                })
                .build();
        dialog.show();
    }


//    public void showUpdatePassWord(Context context, AddOnClikListerPass listener) {
//
//        CustomDialog.Builder builder = new CustomDialog.Builder(context);
//        dialog = builder
//                .style(R.style.DialogOne)
//                .heightdp(230)
//                .widthdp(280)
//                .cancelTouchout(false)
//                .view(R.layout.item_dialog_pass)
//                .addViewOnclick(R.id.tv_cancel_dialog, v -> {
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                })
//                .addViewOnclick(R.id.tv_sure_del, v -> {
//                    if (listener != null) {
//                        String oldPass = builder.oldPass.getText().toString();
//                        String newPass = builder.newPass.getText().toString();
//
//                        if (TextUtils.isEmpty(oldPass)){
//                            Toast.makeText(context, "旧密码不能为空", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (TextUtils.isEmpty(newPass)){
//                            Toast.makeText(context, "新密码密码不能为空", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (dialog != null && dialog.isShowing()) {
//                            dialog.dismiss();
//                        }
//                        listener.setOnclickLister(oldPass,newPass);
//                    }
//
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                })
//                .build();
//        dialog.show();
//    }

}
