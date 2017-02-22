package com.spy.easyframe.module.TwoModule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spy.easyframe.Chart.TickChart;
import com.spy.easyframe.R;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.module.BaseImpl.IChartView;
import com.spy.easyframe.presenter.ChartPersenter;
import com.spy.easyframe.ui.BaseActivity;

public class TwoActivity extends BaseActivity implements IChartView{
    private ChartPersenter chartPersenter;
    private TickChart tick_chart;
    private Button refrash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        tick_chart= (TickChart) findViewById(R.id.tick_chart);
        refrash= (Button) findViewById(R.id.refrash);
    }

    @Override
    protected void initData() {
        chartPersenter=new ChartPersenter(this);
        chartPersenter.getDate(subscription,"3");
    }

    @Override
    protected void initListener() {
        refrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartPersenter.getDate(subscription,"3");
            }
        });
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
        int count=21*60+1;
        tick_chart.setLineData(model,count);
    }
}
