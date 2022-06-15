package com.abc.code.wight;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


public class ChartTools {

    public static Line initLine(List<PointValue> mPointValues, int color, boolean cubic) {
        Line line = new Line(mPointValues).setColor(color);  //折线的颜色（橙色）
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(cubic);//曲线是否平滑，即是曲线还是折线
        line.setFilled(cubic);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        return line;
    }

    //身高
    public static LineChartView initInspectChartViewFinishRateHeight(LineChartView lineChart, List<Line> lines, List<AxisValue> mAxisXValues) {
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("cm");//y轴标注
        axisY.setTextSize(10);//设置字体大小
//        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setHasLines(true);
        //data.setAxisYRight(axisY);  //y轴设置在右边

        float[] dataY = {50,70,90,110,130,150,170,190,210,230,250};
        // 这样添加y轴坐标 就可以固定y轴的数据
        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < dataY.length; i++) {
            AxisValue value = new AxisValue(dataY[i]);
            values.add(value);
        }
        axisY.setValues(values);
        data.setAxisYLeft(axisY);

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setZoomEnabled(false);
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        //固定Y轴的范围,如果没有这个,Y轴的范围会根据数据的最大值和最小值决定,这不是我想要的

        v.top = 250;
        v.bottom = 50;
        lineChart.setMaximumViewport(v);

        Viewport v1 = new Viewport(lineChart.getMaximumViewport());
        v1.left = 0;
        v1.right =7;
        lineChart.setCurrentViewport(v1);
        return lineChart;
    }



    public static LineChartView initInspectChartViewFinishRateWeight(LineChartView lineChart, List<Line> lines, List<AxisValue> mAxisXValues) {

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部

        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("KG");//y轴标注
        axisY.setTextSize(10);//设置字体大小
//        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setHasLines(true);
        //data.setAxisYRight(axisY);  //y轴设置在右边

        float[] dataY = {0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};
        // 这样添加y轴坐标 就可以固定y轴的数据
        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < dataY.length; i++) {
            AxisValue value = new AxisValue(dataY[i]);
            values.add(value);
        }
        axisY.setValues(values);
        data.setAxisYLeft(axisY);

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setZoomEnabled(false);
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        //固定Y轴的范围,如果没有这个,Y轴的范围会根据数据的最大值和最小值决定,这不是我想要的

        v.top = 100;
        v.bottom = 0;
        lineChart.setMaximumViewport(v);

        Viewport v1 = new Viewport(lineChart.getMaximumViewport());
        v1.left = 0;
        v1.right =7;
        lineChart.setCurrentViewport(v1);
        return lineChart;
    }

}
