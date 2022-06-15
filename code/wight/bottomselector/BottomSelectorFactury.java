package com.abc.code.wight.bottomselector;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.abc.code.R;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;

public class BottomSelectorFactury {
	public static final int TYPE_LIST = 1;
	public static final int TYPE_CUSTOM = 2;
	private static BottomSelectorFactury self;
	@BindView(R.id.bottomselect_cancel)
    TextView bottomselectCancel;
	@BindView(R.id.bottomselect_title)
    TextView bottomselectTitle;
	@BindView(R.id.bottomselect_confirm)
    TextView bottomselectConfirm;
	@BindView(R.id.bottomselect_container)
    LinearLayout bottomselectContainer;
	@BindView(R.id.bottomselect_listview)
    ListView bottomselectListview;

	public static synchronized BottomSelectorFactury getInstance() {
		if (self == null) {
			self = new BottomSelectorFactury();
		}
		return self;
	}

	public Dialog getListDialog(Context c, String title, final List<? extends Object> data, String displayField, final IBottomSheetCallback callback) {
		final Dialog bottomDialog = new Dialog(c, R.style.BottomDialog);
		View v = LayoutInflater.from(c).inflate(R.layout.layout_bottomselector, null);
		bottomDialog.setCancelable(true);//设置点击外部是否可以取消
		bottomDialog.setCanceledOnTouchOutside(true);
		TextView bottomselectCancel = v.findViewById(R.id.bottomselect_cancel);
		TextView bottomselectTitle = v.findViewById(R.id.bottomselect_title);
		TextView bottomselectConfirm = v.findViewById(R.id.bottomselect_confirm);
		LinearLayout bottomselectContainer = v.findViewById(R.id.bottomselect_container);
		ListView bottomselectListview = v.findViewById(R.id.bottomselect_listview);
		bottomselectTitle.setText(title);
		bottomselectListview.setVisibility(View.VISIBLE);
		bottomselectContainer.setVisibility(View.GONE);
		bottomselectListview.setAdapter(new simpleBottomAdapter(c, data, displayField));
		bottomselectListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				callback.onSelectItem(data.get(i));
				bottomDialog.dismiss();
			}
		});
		setListViewHeight(bottomselectListview);
		bottomDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {
				callback.onClickCancel();
			}
		});
		bottomselectCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callback.onClickCancel();
				bottomDialog.dismiss();
			}
		});
		bottomselectConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callback.onClickConfirm();
				bottomDialog.dismiss();
			}
		});
		bottomDialog.setContentView(v);
		ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
		layoutParams.width = c.getResources().getDisplayMetrics().widthPixels;
		v.setLayoutParams(layoutParams);
        Window window = bottomDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.transparent);
//		bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
		return bottomDialog;
	}

	public Dialog getCustomDialog(Context c, String title, View customView, final  IBottomSheetCallback callback) {
		final Dialog bottomDialog = new Dialog(c, R.style.BottomDialog);
		View v = LayoutInflater.from(c).inflate(R.layout.layout_bottomselector, null);
		bottomDialog.setCancelable(true);//设置点击外部是否可以取消
		bottomDialog.setCanceledOnTouchOutside(true);
		TextView bottomselectCancel = v.findViewById(R.id.bottomselect_cancel);
		TextView bottomselectTitle = v.findViewById(R.id.bottomselect_title);
		TextView bottomselectConfirm = v.findViewById(R.id.bottomselect_confirm);
		LinearLayout bottomselectContainer = v.findViewById(R.id.bottomselect_container);
		ListView bottomselectListview = v.findViewById(R.id.bottomselect_listview);
		bottomselectTitle.setText(title);
		bottomselectListview.setVisibility(View.GONE);
		bottomselectContainer.setVisibility(View.VISIBLE);
		bottomselectContainer.addView(customView);
		bottomDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {
				callback.onClickCancel();
			}
		});
		bottomselectCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callback.onClickCancel();
				bottomDialog.dismiss();
			}
		});
		bottomselectConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callback.onClickConfirm();
				bottomDialog.dismiss();
			}
		});
		bottomDialog.setContentView(v);
		ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
		layoutParams.width = c.getResources().getDisplayMetrics().widthPixels;
		v.setLayoutParams(layoutParams);
        Window window = bottomDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.transparent);
		return bottomDialog;

	}

	private void setListViewHeight(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
		if (listAdapter == null) {
			return;
		}

		View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
		//进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
		itemView.measure(0, 0);
		int itemHeight = itemView.getMeasuredHeight(); //一项的高度
		int itemCount = listAdapter.getCount();//得到总的项数
		LinearLayout.LayoutParams layoutParams = null; //进行布局参数的设置
		if (itemCount <= 4) {
			layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * itemCount);
		} else if (itemCount > 4) {
			layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * 4);
		}
		listView.setLayoutParams(layoutParams);
	}

	private class simpleBottomAdapter extends BaseAdapter {
		List<? extends Object> mData;
		String field;
		Context c;

		public simpleBottomAdapter(Context c, List<? extends Object> data, String field) {
			mData = data;
			this.c = c;
			this.field = field;
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			View v = LayoutInflater.from(c).inflate(R.layout.item_bottomselect, null);
			TextView t = v.findViewById(R.id.bottomselector_item_text);
			if (mData.size() <= i) {
				return v;
			}
			if (mData != null && mData.get(i) != null) {
				if (TextUtils.isEmpty(field)) {
					t.setText(mData.get(i).toString());
				} else {
					t.setText(getDisplayName(mData.get(i), field));
				}
			}
			return v;
		}

		private String getDisplayName(Object obj, String fieldname) {
			if (obj == null) {
				return null;
			}
			try {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field tempfield : fields) {
					tempfield.setAccessible(true);
					if (tempfield.getName().equalsIgnoreCase(fieldname)) {
						return (String) tempfield.get(obj);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "解析失败";
		}

	}


}
