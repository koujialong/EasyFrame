package com.spy.easyframe.module.FourModule;

import android.os.Bundle;

import com.spy.easyframe.Chart.BarChart;
import com.spy.easyframe.Chart.CandleStackChart;
import com.spy.easyframe.Chart.CoupleChartGestureListener;
import com.spy.easyframe.Chart.TickChart;
import com.spy.easyframe.R;
import com.spy.easyframe.model.KLineModel;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.module.BaseImpl.IKLineChartView;
import com.spy.easyframe.module.BaseImpl.ITickChartView;
import com.spy.easyframe.presenter.CandleStickChartPresenter;
import com.spy.easyframe.presenter.TickChartPersenter;
import com.spy.easyframe.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FourActivity extends BaseActivity implements ITickChartView, IKLineChartView {
    @Bind(R.id.candle_chart)
    CandleStackChart candleChart;
    @Bind(R.id.bar_chart)
    BarChart barChart;
    @Bind(R.id.chart)
    TickChart chart;
    private TickChartPersenter chartPersenter;
    private CandleStickChartPresenter candleStickChartPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    @Override
    protected void initData() {
        chartPersenter = new TickChartPersenter(this);
        chartPersenter.getDate(subscription, "3");
        candleStickChartPresenter = new CandleStickChartPresenter(this);
        candleStickChartPresenter.getDate(subscription, "3");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void injectComponent() {

    }

    @Override
    public String getActivityTag() {
        return null;
    }

    @Override
    public void showChart(TickChartModel model) {
        chart.setLineData(model, 21 * 60 + 1);
    }

    @Override
    public void showChart(KLineModel model) {
        candleChart.setCandleData(model);
        barChart.setBarData(model);
        candleChart.setOnChartGestureListener(new CoupleChartGestureListener(model,candleChart,barChart));
        barChart.setOnChartGestureListener(new CoupleChartGestureListener(model,barChart,candleChart));
    }
}
