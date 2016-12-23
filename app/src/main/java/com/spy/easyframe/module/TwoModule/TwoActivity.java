package com.spy.easyframe.module.TwoModule;

import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.ui.BaseActivity;

public class TwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public String getActivityTag() {
        return null;
    }
}
