package com.spy.easyframe.module.ThreeModule;

import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.ui.BaseActivity;

public class ThreeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
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
