package com.spy.easyframe.Chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.spy.easyframe.R;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.util.TimeUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class TickChart extends CombinedChart{
    private Context mContext;
    public TickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    public TickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
    }

    public TickChart(Context context) {
        super(context);
        mContext=context;
    }

    public void setLineData(TickChartModel model,int xCount){
        List<Entry> yValues=new ArrayList<>();
        List<Entry> yExtValues=new ArrayList<>();
        List<Entry> yAverageValues=new ArrayList<>();
        List<List<String>> list = model.getItem().getDatas();
        for (int i = 0; i < list.size(); i++) {
            yValues.add(new Entry(i,Float.parseFloat(list.get(i).get(1))));
            yAverageValues.add(new Entry(i,Float.parseFloat(model.getItem().getDatas2().get(i).get(1))));
        }

        for (int i = 0; i < xCount; i++) {
            yExtValues.add(new Entry(i,Float.parseFloat("0")));
        }

        initTickChart(model);
        showChart(yValues,yAverageValues,yExtValues);
    }

    private void showChart(List<Entry> yValues,List<Entry> yAverageValues,List<Entry> yExtValues) {
        CombinedData combinedData=new CombinedData();
        List<ILineDataSet> sets=new ArrayList<>();
        sets.add(initTickChartData(yValues,Color.BLUE,true,true));
        sets.add(initTickChartData(yExtValues,Color.TRANSPARENT,false,false));
        sets.add(initTickChartData(yAverageValues,Color.YELLOW,true,false));
        LineData lineData=new LineData(sets);
        combinedData.setData(lineData);
        setData(combinedData);
        postInvalidate();
    }

    /**
    * 绘制分时图
    * @date: 2017/2/22 16:29
    * @author: KJL
    * @param:
    * @return:
    */
    private void initTickChart(final TickChartModel model) {
        setDescription(null);
        //计算图表上下偏移量
        final float highValue = (float) model.getItem().getHightest();
        final float lowValue = (float) model.getItem().getLowest();
        final float averageValue = (float) model.getItem().getSettlement();
        final float value1 = Math.abs(highValue - averageValue);
        final float value2 = Math.abs(lowValue - averageValue);
        final float buffer = value1 > value2 ? value1 / 10 + value1 : value2 / 10 + value2;

        //设置x轴属性
        XAxis xAxis=getXAxis();
        //防止x头尾数据不完全显示
        xAxis.setAvoidFirstLastClipping(true);
        //设置x轴轴线位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置背景网格虚线密度
        xAxis.enableGridDashedLine(5f,5f,0f);
        //设置背景网格虚线颜色
        xAxis.setGridColor(Color.BLACK);
        //设置x轴轴线颜色
        xAxis.setAxisLineColor(Color.BLACK);
        //设置刻度字体大小
        xAxis.setTextSize(8f);
        //设置刻度字体颜色
        xAxis.setTextColor(Color.BLACK);
        //设置刻度显示数量
        xAxis.setLabelCount(5,true);
        //设置显示轴线
        xAxis.setDrawAxisLine(true);
        //设置x轴绘制动画的时间
        animateX(400);
        //x轴数据格式化
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if (v>=model.getItem().getDatas().size()){
                    return "";
                }else {
                    return TimeUtils.getTimeHHMM(Long.parseLong(model.getItem().getDatas().get((int) v).get(0)));
                }
            }
        });


        //左侧Y轴设置
        YAxis leftAxis=getAxisLeft();
        //设置背景网格虚线密度
        leftAxis.enableGridDashedLine(5f,5f,0f);
        //设置网格虚线的颜色
        leftAxis.setGridColor(Color.BLACK);
        //设置显示刻度数量
        leftAxis.setLabelCount(7,true);
        //设置刻度字体大小
        leftAxis.setTextSize(8f);
        //设置刻度字体颜色
        leftAxis.setTextColor(Color.BLACK);
        //设置轴线颜色
        leftAxis.setAxisLineColor(Color.BLACK);
        //设置开启Y轴颜色区分
        leftAxis.setDrawDifferentUpDownColorToLableEnabled(true);
        //设置轴线上半区颜色
        leftAxis.setUpTextColor(Color.RED);
        //设置轴线下半区颜色
        leftAxis.setDownTextColor(Color.GREEN);
        //设置刻度是否从0开始
        leftAxis.setStartAtZero(false);
        //设置最大刻度值
        leftAxis.setAxisMaxValue(averageValue+buffer);
        //设置最小刻度值
        leftAxis.setAxisMinValue(averageValue-buffer);
        //格式化刻度数据
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return (new DecimalFormat("#0.00").format(v));
            }
        });

        //左侧Y轴设置
        YAxis rightAxis=getAxisRight();
        //设置背景网格虚线密度
        rightAxis.enableGridDashedLine(5f,5f,0f);
        //设置网格虚线的颜色
        rightAxis.setGridColor(Color.BLACK);
        //设置显示刻度数量
        rightAxis.setLabelCount(7,true);
        //设置刻度字体大小
        rightAxis.setTextSize(8f);
        //设置刻度字体颜色
        rightAxis.setTextColor(Color.BLACK);
        //设置轴线颜色
        rightAxis.setAxisLineColor(Color.BLACK);
        //设置开启Y轴颜色区分
        rightAxis.setDrawDifferentUpDownColorToLableEnabled(true);
        //设置轴线上半区颜色
        rightAxis.setUpTextColor(Color.RED);
        //设置轴线下半区颜色
        rightAxis.setDownTextColor(Color.GREEN);
        //设置刻度是否从0开始
        rightAxis.setStartAtZero(false);
        //设置最大刻度值
        rightAxis.setAxisMaxValue(averageValue+buffer);
        //设置最小刻度值
        rightAxis.setAxisMinValue(averageValue-buffer);
        //格式化刻度数据
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return (new DecimalFormat("#0.00").format(v));
            }
        });

        //设置图表边框
        setDrawBorders(true);
        //设置边框宽度
        setBorderWidth(0.4f);
        //设置边框颜色
        setBorderColor(Color.BLACK);
        //设置图表当前显示位置
        moveViewToX(0);
    }

    /**
    * 初始化分时图数据属性
    * @date: 2017/2/22 16:07
    * @author: KJL
    * @param:
    * @return:
    */
    private ILineDataSet initTickChartData(List<Entry> yVaues,int lineColor,boolean isShow,boolean isFill) {
        //创建数据集合
        LineDataSet lineDataSet=new LineDataSet(yVaues,"");
        //设置线宽
        lineDataSet.setLineWidth(1f);
        //设置线的颜色
        lineDataSet.setColor(lineColor);
        //设置选中的高亮颜色
        lineDataSet.setHighLightColor(Color.BLACK);
        //数据点圆圈标识开关
        lineDataSet.setDrawCircles(false);
        //数据点数据标识开关
        lineDataSet.setDrawValues(false);
        //线下填充颜色设置
        if (isFill) {
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.bg_fade_tick);
            lineDataSet.setFillDrawable(drawable);
            lineDataSet.setDrawFilled(true);
        }
        //设置是否显示
        lineDataSet.setVisible(isShow);

        return lineDataSet;
    }
}
