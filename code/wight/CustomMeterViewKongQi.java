package com.abc.code.wight;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.abc.code.R;

/**
 * @author: hedianbo.
 * @Contanct: 1040080642@qq.com
 * @date: 2020/5/9 4:57 PM.
 * @desc:
 */
public class CustomMeterViewKongQi extends View {


    //白色圆弧画笔
    private Paint whiteArcPaint;

    //蓝色圆弧画笔
    private Paint blueArcPaint;

    //称重数据画笔
    private Paint weightDataPaint;

    //刻度数据画笔
    private Paint scaleDataPaint;

    //圆弧矩形范围
    private RectF oval;

    //当前称重数据
    private float currentData;

    //圆弧经过的角度范围
    private float sweepAngle = 240;


    public CustomMeterViewKongQi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        whiteArcPaint = new Paint();
        whiteArcPaint.setAntiAlias(true);
        whiteArcPaint.setTextSize(37);
        whiteArcPaint.setColor(Color.BLUE);
        whiteArcPaint.setStyle(Paint.Style.STROKE);

        blueArcPaint = new Paint();
        blueArcPaint.setAntiAlias(true);
        blueArcPaint.setColor(Color.parseColor("#3a84f4"));
        blueArcPaint.setStyle(Paint.Style.STROKE);
        blueArcPaint.setShadowLayer((float)10,(float)10,(float)10,Color.parseColor("#99000000"));

        weightDataPaint = new Paint();
        weightDataPaint.setColor(Color.BLUE);
        weightDataPaint.setTextSize(37);
        weightDataPaint.setStyle(Paint.Style.STROKE);

        scaleDataPaint = new Paint();
        scaleDataPaint.setColor(Color.BLUE);
        scaleDataPaint.setTextSize(37);

        currentData = 0;

        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);

        whiteArcPaint.setStrokeWidth(width * (float)0.1);
        blueArcPaint.setStrokeWidth(width * (float)0.12);

        oval = new RectF(width * (float)0.2,width * (float) 0.2,width * (float) 0.8 ,width * (float) 0.8);

        setMeasuredDimension(width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWhiteArc(canvas);
        drawBlueArc(canvas);
        drawArrow(canvas);
        drawWeightingData(canvas);
        drawScaleData(canvas);

    }

    private void drawWhiteArc(Canvas canvas){
        canvas.save();
        canvas.drawArc(oval,150,sweepAngle,false,whiteArcPaint);
    }

    private void drawBlueArc(Canvas canvas){
        canvas.save();
        canvas.drawArc(oval,150,sweepAngle * currentData / 100,false,blueArcPaint);
    }

    private void drawArrow(Canvas canvas){
        canvas.save();
        Bitmap oldBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_1);
        int width = oldBitmap.getWidth();
        int height = oldBitmap.getHeight();
        int newWidth = (int) (getWidth() * 0.08);
        float scaleWidth = ((float)newWidth) / width;
        float scaleHeight = ((float)newWidth) / height;
        Matrix matrix = new Matrix();
        //TODO 下面这两个顺序不能变
        matrix.setRotate(-120 + (sweepAngle * currentData / 100),oldBitmap.getWidth() / 2,oldBitmap.getHeight() / 2);
        matrix.postScale(scaleWidth,scaleHeight);

        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap,0,0,width,height,matrix,true);
        canvas.drawBitmap(newBitmap,getWidth() / 2 - newBitmap.getWidth() / 2, getHeight() / 2 - newBitmap.getHeight() / 2,whiteArcPaint);
        oldBitmap.recycle();
        newBitmap.recycle();
    }

    private void drawWeightingData(Canvas canvas){
        canvas.save();
        Rect rect = new Rect();
        String data = String.valueOf(currentData);
        weightDataPaint.setStyle(Paint.Style.FILL);
        weightDataPaint.getTextBounds(data,0,data.length(),rect);
        canvas.drawText(data,getWidth()/2 - rect.width()/2,(int) (getHeight() * (float)0.38),weightDataPaint);
    }

    private void drawScaleData(Canvas canvas){
        canvas.save();
        Rect startScaleText = new Rect();
        String data1 = "0";
        scaleDataPaint.getTextBounds(data1,0,data1.length(),startScaleText);
        int height1 = (int) (getWidth() * 0.3 * Math.cos(Math.PI / 6) / 2 + getWidth() * 0.6 * 0.5 + getWidth() * 0.25);
        int width1 = (int) ((getWidth() * 0.2 + getWidth() * 0.3 *0.25) - startScaleText.width() - getWidth() * 0.1);
        canvas.drawText(data1,width1,height1,scaleDataPaint);
        canvas.save();

        Rect endScaleText = new Rect();
        String data2 = "100";
        scaleDataPaint.getTextBounds(data2,0,data2.length(),endScaleText);
        int width2 = (int)(getWidth() * 0.8 - getWidth() * 0.3 *0.25 + getWidth() * 0.1);
        canvas.drawText(data2,width2,height1,scaleDataPaint);
        canvas.save();

        Rect middleScaleText = new Rect();
        String data3 = "50";
        scaleDataPaint.getTextBounds(data3,0,data3.length(),middleScaleText);
        int width3 = getWidth() / 2 - middleScaleText.width() / 2;
        int height2 = (int) (getWidth() * 0.12);
        canvas.drawText(data3,width3,height2,scaleDataPaint);
        canvas.save();

        Rect weighingText = new Rect();
        String data4 = "空气湿度";
        scaleDataPaint.getTextBounds(data4,0,data4.length(),weighingText);
        int width4 = getWidth() / 2 - weighingText.width() / 2;
        int height = (int) (getWidth() * 0.74);
//        scaleDataPaint.setTextSize(22);
        canvas.drawText(data4,width4,height,scaleDataPaint);
        canvas.save();
    }

    public void setPercentData(final float data, TimeInterpolator interpolator){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(currentData,data);
        valueAnimator.setDuration((long) (Math.abs(currentData-data)*10));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value= (float) valueAnimator.getAnimatedValue();
                Log.e("value","value is :" + value);
                currentData=(float)(Math.round(value*10))/10;
                invalidate();
            }
        });
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.start();
    }
}
