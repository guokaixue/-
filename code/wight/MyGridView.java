package com.abc.code.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author: hedianbo.
 * date: 2020-03-02
 * desc:
 */
public class MyGridView extends GridView {


    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * Integer.MAX_VALUE >> 2的含义是Int类型的最大值的二进制向右移动2位的值
         *
         */
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
