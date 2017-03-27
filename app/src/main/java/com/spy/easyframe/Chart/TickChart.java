package com.spy.easyframe.Chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.spy.easyframe.R;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.TimeUtils;

import java.math.BigDecimal;
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

        showChart(yValues,yAverageValues,yExtValues,model);
        initTickChart(model);
        initListener(model);
    }

    /**
    * 绘制分时图
    * @date: 2017/2/23 9:45
    * @author: KJL
    * @param:
    * @return:
    */
    private void showChart(List<Entry> yValues,List<Entry> yAverageValues,List<Entry> yExtValues,TickChartModel model) {
        CombinedData combinedData=new CombinedData();
        List<ILineDataSet> sets=new ArrayList<>();
        sets.add(initTickChartData(yValues,Color.GRAY,true,true));
        sets.add(initTickChartData(yExtValues,Color.TRANSPARENT,false,false));
        sets.add(initTickChartData(yAverageValues,Color.RED,true,false));
        LineData lineData=new LineData(sets);
        combinedData.setData(lineData);
        setData(combinedData);
        //设置marker数据
        TickChartMarkerView markerView=new TickChartMarkerView(model);
        setMarker(markerView);
        postInvalidate();
    }

    /**
    * 初始化分时图图表属性
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
                long l = Long.parseLong(model.getItem().getDatas().get(0).get(0));
                if (v==0){
                    return TimeUtils.getTimeHHMM(l);
                }else {
                    LogUtils.e("TAG",v+"");
//                    if ((int)v==270) {
                        BigDecimal offsetTime = new BigDecimal(v * 60 * 1000);
                        long time = l + Long.parseLong(offsetTime.toString());
                        return TimeUtils.getTimeHHMM(time);
//                    }else {
//                        return "";
//                    }
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
        //设置刻度值显示位置
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
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

        //右侧Y轴设置
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
        //设置刻度值显示位置
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
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

        //图例设置
        Legend legend=getLegend();
        //设置不显示图例
        legend.setEnabled(false);

        //设置图表边框
        setDrawBorders(true);
        //设置边框宽度
        setBorderWidth(0.4f);
        //设置边框颜色
        setBorderColor(Color.BLACK);

        //设置图表手势
        //设置拖动
        setDragEnabled(true);
        //设置禁止x轴上的缩放
        setScaleXEnabled(false);
        //设置禁止y轴上的缩放
        setScaleYEnabled(false);
        //设置关闭捏缩功能
        setPinchZoom(false);
        //设置双击缩放图表
        setDoubleTapToZoomEnabled(false);
        //设置滑动高亮显示
        setHighlightPerDragEnabled(false);
        //设置点击高亮显示
        setHighlightPerTapEnabled(false);

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
        //设置数据高亮显示
        if (isFill) {
            lineDataSet.setHighlightEnabled(true);
        }else {
            lineDataSet.setHighlightEnabled(false);
        }
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

    /**
    * 初始化图表监听
    * @date: 2017/2/23 14:12
    * @author: KJL
    * @param:
    * @return:
    */
    private void initListener(TickChartModel model) {
        this.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                highlightValue(highlight);
            }

            @Override
            public void onNothingSelected() {

            }
        });

        this.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent motionEvent) {
                setHighlightPerDragEnabled(true);
            }

            @Override
            public void onChartDoubleTapped(MotionEvent motionEvent) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent motionEvent) {
                setHighlightPerDragEnabled(false);
                highlightValue(null);
            }

            @Override
            public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

            }

            @Override
            public void onChartScale(MotionEvent motionEvent, float v, float v1) {

            }

            @Override
            public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {

            }
        });
    }
}
