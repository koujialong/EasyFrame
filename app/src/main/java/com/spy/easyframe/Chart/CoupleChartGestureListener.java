package com.spy.easyframe.Chart;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.spy.easyframe.model.KLineModel;

/**
 * Created by Administrator on 2017/2/24.
 */
public class CoupleChartGestureListener implements OnChartGestureListener{

    private CombinedChart srcChart,desChart;
    private KLineModel kLineModel;

    public CoupleChartGestureListener(KLineModel kLineModel, CombinedChart srcChart, CombinedChart desChart) {
        this.kLineModel = kLineModel;
        this.srcChart = srcChart;
        this.desChart = desChart;
        syncCharts();
        highLightSync();
    }

    @Override
    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent motionEvent) {
        lockChart();
       //LogUtils.e("TAG",motionEvent.getActionIndex()+"");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent motionEvent) {
        unlockChart();
    }

    @Override
    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        syncCharts();
    }

    @Override
    public void onChartScale(MotionEvent motionEvent, float v, float v1) {
        syncCharts();
    }

    @Override
    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {
        syncCharts();
    }

    /**
    * 锁住图表
    * @date: 2017/2/24 15:55
    * @author: KJL
    * @param:
    * @return:
    */
    public void lockChart(){
        srcChart.setDragEnabled(false);
        srcChart.setHighlightPerDragEnabled(true);
        desChart.setDragEnabled(false);
        desChart.setHighlightPerDragEnabled(true);
    }

    /**
    * 解锁图表
    * @date: 2017/2/24 16:06
    * @author: KJL
    * @param:
    * @return:
    */
    public void unlockChart(){
        srcChart.setDragEnabled(true);
        srcChart.setHighlightPerDragEnabled(false);
        desChart.setDragEnabled(true);
        desChart.setHighlightPerDragEnabled(false);
        srcChart.highlightValue(null);
        desChart.highlightValue(null);
    }

    /**
    * 同步图表位置
    * @date: 2017/2/24 15:32
    * @author: KJL
    * @param:
    * @return:
    */
    public void syncCharts(){
        Matrix srcMatrix;
        float[] srcVals=new float[9];
        Matrix desMatrix;
        float[] desVals=new float[9];

        //获得源图转换矩阵
        srcMatrix=srcChart.getViewPortHandler().getMatrixTouch();
        srcMatrix.getValues(srcVals);

        //同步源图和目标图的图形矩阵
        if (desChart.getVisibility()== View.VISIBLE){
            desMatrix=desChart.getViewPortHandler().getMatrixTouch();
            desMatrix.getValues(desVals);

            desVals[Matrix.MSCALE_X]=srcVals[Matrix.MSCALE_X];
            desVals[Matrix.MSKEW_X] = srcVals[Matrix.MSKEW_X];
            desVals[Matrix.MTRANS_X] = srcVals[Matrix.MTRANS_X];
            desVals[Matrix.MSKEW_Y] = srcVals[Matrix.MSKEW_Y];
            desVals[Matrix.MSCALE_Y] = srcVals[Matrix.MSCALE_Y];
            desVals[Matrix.MTRANS_Y] = srcVals[Matrix.MTRANS_Y];
            desVals[Matrix.MPERSP_0] = srcVals[Matrix.MPERSP_0];
            desVals[Matrix.MPERSP_1] = srcVals[Matrix.MPERSP_1];
            desVals[Matrix.MPERSP_2] = srcVals[Matrix.MPERSP_2];

            desMatrix.setValues(desVals);
            desChart.getViewPortHandler().refresh(desMatrix,desChart,true);
        }

    }

    /**
    * 同步高亮数据
    * @date: 2017/2/24 15:31
    * @author: KJL
    * @param:
    * @return:
    */
    private void highLightSync(){
        srcChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                desChart.highlightValues(new Highlight[]{highlight});
                desChart.setMaxVisibleValueCount(srcChart.getMaxVisibleCount());
            }

            @Override
            public void onNothingSelected() {
                desChart.highlightValue(null);
            }
        });

        desChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                srcChart.highlightValues(new Highlight[]{highlight});
                srcChart.setMaxVisibleValueCount(desChart.getMaxVisibleCount());
            }

            @Override
            public void onNothingSelected() {
                srcChart.highlightValue(null);
            }
        });
    }

}
