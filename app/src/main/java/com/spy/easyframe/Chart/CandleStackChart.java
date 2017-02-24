package com.spy.easyframe.Chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
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
import com.spy.easyframe.model.KLineModel;
import com.spy.easyframe.util.TimeUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/23.
 */
public class CandleStackChart extends CombinedChart{

    public CandleStackChart(Context context) {
        super(context);
    }

    public CandleStackChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandleStackChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCandleData(KLineModel kLineModel){
        //蜡烛数据集合
        List<CandleEntry> candleValues=new ArrayList<>();
        //辅助线1数据集合
        List<Entry> lineValues1=new ArrayList<>();
        //辅助线2数据集合
        List<Entry> lineValues2=new ArrayList<>();
        //辅助线3数据集合
        List<Entry> lineValues3=new ArrayList<>();

        //数据转换
        List<List<String>> datas = kLineModel.getItem().getDatas();
        for (int i = 0; i < datas.size(); i++) {
            List<String> list = datas.get(i);
            CandleEntry entry=new CandleEntry(i,Float.parseFloat(list.get(2)),
                    Float.parseFloat(list.get(3)),
                    Float.parseFloat(list.get(1)),
                    Float.parseFloat(list.get(4)));
            candleValues.add(entry);
        }

        List<List<String>> baseLine = kLineModel.getItem().getBaseLine();
        for (int i = 0; i < baseLine.size(); i++) {
            List<String> list = baseLine.get(i);
            lineValues1.add(new Entry(i,Float.parseFloat(list.get(0))));
            lineValues2.add(new Entry(i,Float.parseFloat(list.get(1))));
            lineValues3.add(new Entry(i,Float.parseFloat(list.get(2))));
        }

        showChart(candleValues,lineValues1,lineValues2,lineValues3,kLineModel);
        initCandleChart(kLineModel);
        initListener(kLineModel);
    }

    /**
     * 绘制蜡烛图
     * @date: 2017/2/23 9:45
     * @author: KJL
     * @param:
     * @return:
     */
    private void showChart(List<CandleEntry> candleValues,List<Entry> lineValues1,List<Entry> lineValues2,
                           List<Entry> lineValues3,KLineModel model) {
        CombinedData combinedData=new CombinedData();
        List<ILineDataSet> sets=new ArrayList<>();
        //添加辅助线，并且设置辅助线颜色
        sets.add(initLineData(lineValues1,Color.CYAN));
        sets.add(initLineData(lineValues2,Color.BLACK));
        sets.add(initLineData(lineValues3,Color.BLUE));
        LineData lineData=new LineData(sets);
        combinedData.setData(lineData);
        combinedData.setData(initCandleStickChartData(candleValues));
        CandleStackChartMarkerView markerView=new CandleStackChartMarkerView(model);
        setMarker(markerView);
        setData(combinedData);
        notifyDataSetChanged();
        postInvalidate();
    }

    /**
    * 初始化蜡烛图图表属性
    * @date: 2017/2/23 17:52
    * @author: KJL
    * @param:
    * @return:
    */
    private void initCandleChart(final KLineModel kLineModel) {
        setDescription(null);

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

        xAxis.setXOffset(10);

        //设置x轴绘制动画的时间
        animateX(400);
        //x轴数据格式化
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return TimeUtils.getTimeMD(Long.parseLong(kLineModel.getItem().getDatas().get((int)v).get(5)));
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
        //设置刻度是否从0开始
        leftAxis.setStartAtZero(false);
//        //设置最大刻度值
//        leftAxis.setAxisMaxValue(averageValue+buffer);
//        //设置最小刻度值
//        leftAxis.setAxisMinValue(averageValue-buffer);
        //格式化刻度数据
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return (new DecimalFormat("#0.00").format(v));
            }
        });

        //右侧Y轴设置
        YAxis rightAxis=getAxisRight();
        //设置不显示刻度数据
        rightAxis.setDrawLabels(false);
        //设置不绘制背景网格
        rightAxis.setDrawGridLines(false);


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
        //打开缩放功能
        setScaleEnabled(true);
        //设置禁止x轴上的缩放
        setScaleXEnabled(true);
        //设置禁止y轴上的缩放
        setScaleYEnabled(false);
        //设置惯性滚动
        setDragDecelerationEnabled(true);
        //设置捏缩功能
        setPinchZoom(true);
        //设置双击缩放图表
        setDoubleTapToZoomEnabled(false);
        //设置滑动高亮显示
        setHighlightPerDragEnabled(false);
        //设置点击高亮显示
        setHighlightPerTapEnabled(false);
        //设置自动匹配Y轴高度
        setAutoScaleMinMaxEnabled(true);

        //设置图表可显示蜡烛个数
        setVisibleXRange(40,40);

        //设置图表当前显示位置
        moveViewToX(kLineModel.getItem().getDatas().size()-1);
    }

    /**
     * 初始化K线图数据属性
     * @date: 2017/2/22 16:07
     * @author: KJL
     * @param:
     * @return:
     */
    private CandleData initCandleStickChartData(List<CandleEntry> candleVaues) {
        //创建数据集合
        CandleDataSet candleDataSet=new CandleDataSet(candleVaues,"");
        //设置蜡烛影线颜色
        candleDataSet.setShadowColor(Color.DKGRAY);
        //设置蜡烛影线宽度
        candleDataSet.setShadowWidth(0.5f);
        //设置蜡烛阳线颜色
        candleDataSet.setIncreasingColor(Color.RED);
        //设置蜡烛阳线风格为填充
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        //设置蜡烛阴线颜色
        candleDataSet.setDecreasingColor(Color.GREEN);
        //设置蜡烛阴线风格为填充
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        //设置影线和阴阳线是否保持同色
        candleDataSet.setShadowColorSameAsCandle(true);
        //设置高亮线颜色
        candleDataSet.setHighLightColor(Color.BLACK);
        //设置是否绘制数据
        candleDataSet.setDrawValues(!candleDataSet.isDrawValuesEnabled());

        CandleData candleData=new CandleData(candleDataSet);
        return candleData;
    }

    /**
    * 初始化数据属性
    * @date: 2017/2/24 14:21
    * @author: KJL
    * @param:
    * @return:
    */
    private LineDataSet initLineData(List<Entry> yValues,int lineColor){
        //创建数据集合
        LineDataSet lineDataSet=new LineDataSet(yValues,"");
        //设置线宽
        lineDataSet.setLineWidth(1f);
        //设置线的颜色
        lineDataSet.setColor(lineColor);
        //设置数据高亮显示
        lineDataSet.setHighlightEnabled(false);
        //设置选中的高亮颜色
        lineDataSet.setHighLightColor(Color.BLACK);
        //数据点圆圈标识开关
        lineDataSet.setDrawCircles(false);
        //数据点数据标识开关
        lineDataSet.setDrawValues(false);

        return lineDataSet;
    }

    /**
     * 初始化图表监听
     * @date: 2017/2/23 14:12
     * @author: KJL
     * @param:
     * @return:
     */
    private void initListener(KLineModel model) {
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
                setDragEnabled(false);
                setHighlightPerDragEnabled(true);
            }

            @Override
            public void onChartDoubleTapped(MotionEvent motionEvent) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent motionEvent) {
                setDragEnabled(true);
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
