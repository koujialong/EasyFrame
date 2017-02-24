package com.spy.easyframe.module.TwoModule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spy.easyframe.Chart.TickChart;
import com.spy.easyframe.R;
import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.module.BaseImpl.ITickChartView;
import com.spy.easyframe.presenter.TickChartPersenter;
import com.spy.easyframe.ui.BaseActivity;

public class TwoActivity extends BaseActivity implements ITickChartView{
    private TickChartPersenter chartPersenter;
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
        chartPersenter=new TickChartPersenter(this);
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
