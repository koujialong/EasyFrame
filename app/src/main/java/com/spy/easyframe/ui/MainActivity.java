package com.spy.easyframe.ui;

import android.app.ActivityGroup;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.spy.easyframe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends ActivityGroup {
    /**
     * param
     */
    public static final int TAB_1_ACTIVITY = 0x00;//页面1
    public static final int TAB_2_ACTIVITY = 0x01;//页面2
    public static final int TAB_3_ACTIVITY = 0x02;//页面3
    public static final int TAB_4_ACTIVITY = 0x03;//页面4
    private MainActivityPresenter mPresenter;

    public static final String EXTRA_TAB_INDEX_KEY = "TAB_INDEX_KEY";// 用来传递从通知栏进入时，需要切换的tab
    @Bind(R.id.tab_homepage)
    RadioButton tabHomepage;
    @Bind(R.id.tab_trade)
    RadioButton tabTrade;
    @Bind(R.id.tab_watch)
    RadioButton tabWatch;
    @Bind(R.id.tab_mine)
    RadioButton tabMine;
    @Bind(R.id.rg_tabs)
    RadioGroup rgTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        switchToOneTab();
        ButterKnife.bind(this);
    }

    private void initView() {
        mPresenter = new MainActivityPresenter(this);
    }

//    @OnClick({R.id.tab_homepage, R.id.tab_trade, R.id.tab_watch, R.id.tab_mine})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tab_homepage:
//                break;
//            case R.id.tab_trade:
//                break;
//            case R.id.tab_watch:
//                break;
//            case R.id.tab_mine:
//                break;
//        }
//    }

    /**
     * 跳转到页2
     */
    public void switchToTwoTab() {
        if (mPresenter == null || mPresenter.getTab() == null) {
            return;
        }
        mPresenter.getTab().check(R.id.tab_trade);
    }

    /**
     * 跳转到页3
     */
    public void switchToThreeTab() {
        if (mPresenter == null || mPresenter.getTab() == null) {
            return;
        }
        mPresenter.getTab().check(R.id.tab_watch);
    }

    /**
     * 跳转到页1
     */
    public void switchToOneTab() {
        if (mPresenter == null || mPresenter.getTab() == null) {
            return;
        }
        mPresenter.getTab().check(R.id.tab_homepage);
    }

    /**
     * 跳转到页4
     */
    public void switchToFourTab() {
        if (mPresenter == null || mPresenter.getTab() == null) {
            return;
        }
        mPresenter.getTab().check(R.id.tab_mine);
    }

}
