package com.spy.easyframe.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.spy.easyframe.R;
import com.spy.easyframe.module.FourModule.FourActivity;
import com.spy.easyframe.module.OneModule.OneActivity;
import com.spy.easyframe.module.ThreeModule.ThreeActivity;
import com.spy.easyframe.module.TwoModule.TwoActivity;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.WeakReferenceHandler;

/**
 * Created by Administrator on 2016/11/11.
 */
public class MainActivityPresenter implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    /**
     * TAG
     */
    private static final String TAG=MainActivityPresenter.class.getSimpleName();
    private SwitchTabHandler mSwitchTabHandler;
    /**
     * view
     */
    private ViewGroup mContentView;
    private final MainActivity mainActivity;
    private final RadioGroup mTab;
    private final RadioButton mHomePageTab;
    private final RadioButton mTradeTab;
    private final RadioButton mWatchTab;
    private final RadioButton mMineTab;



    private static final int SWITCH_TAB_MSG = 0x102;

    public MainActivityPresenter(MainActivity activity) {
        this.mainActivity = activity;
        mSwitchTabHandler = new SwitchTabHandler(activity);
        mContentView = (ViewGroup) findViewById(R.id.content_view);
        mHomePageTab = (RadioButton) findViewById(R.id.tab_homepage);
        mTradeTab = (RadioButton) findViewById(R.id.tab_trade);
        mWatchTab = (RadioButton) findViewById(R.id.tab_watch);
        mMineTab = ((RadioButton) findViewById(R.id.tab_mine));
        mHomePageTab.setOnCheckedChangeListener(this);
        mTradeTab.setOnCheckedChangeListener(this);
        mWatchTab.setOnCheckedChangeListener(this);
        mMineTab.setOnCheckedChangeListener(this);
        mTab = (RadioGroup) findViewById(R.id.rg_tabs);
        initTabs(mainActivity.getIntent());
    }

    public View findViewById(int id) {
        return mainActivity.findViewById(id);
    }


    @Override
    public void onClick(View v) {

    }

    @SuppressLint("HandlerLeak")
    private class SwichTabHandler extends WeakReferenceHandler<MainActivity> {
        public SwichTabHandler(MainActivity reference){
            super(reference);
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void handleMessage(MainActivity reference, Message msg) {
            if ((reference!=null&&reference.isFinishing())){
                return;
            }
            switch (msg.what){
                case SWITCH_TAB_MSG:
                    @SuppressWarnings("unchecked")
                    Pair<Class<?>,ViewGroup> data=(Pair<Class<?>,ViewGroup>)msg.obj;
                    if (data==null){
                        return;
                    }

                    if (!(data.second.getChildAt(0) instanceof RadioGroup)){
                        data.second.removeViewAt(0);
                    }

            }
        }
    }

    private Class<?> convertIndexToClass(int tabIndex){
        switch (tabIndex){
            case MainActivity.TAB_1_ACTIVITY:
                return null;
            case MainActivity.TAB_2_ACTIVITY:
                return null;
            case MainActivity.TAB_3_ACTIVITY:
                return null;
            case MainActivity.TAB_4_ACTIVITY:
                return null;
            default:
                break;
        }
        return null;
    }

    /**
     * @return tab选中的activity
     */
    @SuppressWarnings("deprecation")
    public Activity getTabActivity() {
        final int checkedId = mTab.getCheckedRadioButtonId();
        Class<?> clazz = null;

        switch (checkedId) {
            case R.id.tab_homepage:
                clazz = OneActivity.class;
                break;
            case R.id.tab_trade:
                clazz = TwoActivity.class;
                break;
            case R.id.tab_watch:
                clazz = ThreeActivity.class;
                break;
            case R.id.tab_mine:
                // clazz = ChannelCategoryActivity.class;
                clazz = FourActivity.class;
                break;
            default:
                clazz = OneActivity.class;
                break;
        }

        return mainActivity.getLocalActivityManager().getActivity(clazz.getSimpleName());
    }

    /**
     * @param buttonView
     * @param isChecked
     * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton,
     *      boolean)
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) {
            return;
        }

        LogUtils.d(TAG, "onCheckedChange");
        final int id = buttonView.getId();
        switch (id) {
            case R.id.tab_homepage:
                mainActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(OneActivity.class, mContentView);
                break;
            case R.id.tab_trade:
                mainActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(TwoActivity.class, mContentView);
                break;
            case R.id.tab_watch:
                mainActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(ThreeActivity.class, mContentView);
                break;
            case R.id.tab_mine:
                mainActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(FourActivity.class, mContentView);
                break;
        }
    }


    /**
     * 切换tab页
     *
     * @param clazz
     * @param contentView
     *            左屏或者右屏的内容区域
     */
    private void switchTab(Class<?> clazz, ViewGroup contentView) {
        final Message msg = Message.obtain();
        msg.obj = new Pair<Class<?>, ViewGroup>(clazz, contentView);
        msg.what = SWITCH_TAB_MSG;
        mSwitchTabHandler.sendMessageDelayed(msg, 0);
    }

    @SuppressLint("HandlerLeak")
    private class SwitchTabHandler extends WeakReferenceHandler<MainActivity> {

        public SwitchTabHandler(MainActivity reference) {
            super(reference);
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void handleMessage(MainActivity reference, Message msg) {
            if ((reference != null && reference.isFinishing())) {
                return;
            }
            switch (msg.what) {
                case SWITCH_TAB_MSG:
                    @SuppressWarnings("unchecked")
                    Pair<Class<?>, ViewGroup> data = (Pair<Class<?>, ViewGroup>) msg.obj;
                    if (data == null) {
                        return;
                    }

                    if (!(data.second.getChildAt(0) instanceof RadioGroup)) {
                        data.second.removeViewAt(0);
                    }

                    final Intent intent = new Intent(mainActivity.getApplication(), data.first)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    final Intent paramsIntent = mainActivity.getIntent();

                    final boolean hasExtra = paramsIntent != null
                            && paramsIntent.hasExtra(MainActivity.EXTRA_TAB_INDEX_KEY)
                            && (data.first == convertIndexToClass(paramsIntent.getIntExtra(
                            MainActivity.EXTRA_TAB_INDEX_KEY, MainActivity.TAB_1_ACTIVITY)));

                    // 包含外部push等入口跳转
                    if (hasExtra) {
                        LogUtils.d(TAG, "hasExtra");
                        intent.putExtras(paramsIntent);
                    }

                    LogUtils.d(TAG, "SwitchTabHandler : " + data.first.getSimpleName());

                    data.second.addView(
                            mainActivity.getLocalActivityManager().startActivity(data.first.getSimpleName(), intent)
                                    .getDecorView(), 0, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT));

                    if (hasExtra) {
                        mainActivity.setIntent(null);
                    }

                    // 维持4个tab对应的activity在BaseActivity的栈内位置
                    final Activity tabActivity = getTabActivity();
                    if (tabActivity == null) {
                        return;
                    }
                    BaseActivity.refreshTopActivity((BaseActivity) tabActivity);
                    break;
            }
        }

        private Class<?> convertIndexToClass(int tabIndex) {
            switch (tabIndex) {
                case MainActivity.TAB_1_ACTIVITY:
                    return OneActivity.class;
                case MainActivity.TAB_2_ACTIVITY:
                    return TwoActivity.class;
                case MainActivity.TAB_3_ACTIVITY:
                    return ThreeActivity.class;
                case MainActivity.TAB_4_ACTIVITY:
                    return FourActivity.class;
                default:
                    break;
            }
            return null;
        }
    }

    /**
     * 处理选中tab
     *
     * @param intent
     *            启动activity传入
     */
    public void initTabs(Intent intent) {
        final RadioGroup tab = mTab;

        // 是否需要重新设定选中tab,比如从通知栏进入
        final boolean isNeedRecheck = (intent != null) && intent.hasExtra(MainActivity.EXTRA_TAB_INDEX_KEY);
        // 是否已经有选中的tab
        final boolean isDefaultChecked = tab.getCheckedRadioButtonId() != -1;

        int index;
        // mIsNotClick = isNeedRecheck;
        if (isNeedRecheck) {// 需要重新设置选中tab，比如从通知栏调起时
            index = intent.getIntExtra(MainActivity.EXTRA_TAB_INDEX_KEY, MainActivity.TAB_1_ACTIVITY);
        } else if (isDefaultChecked) {// 不需要重新设置tab，并且曾经已经有默认选中tab。无需处理
            return;
        } else {// 初始化
            index = MainActivity.TAB_1_ACTIVITY;
        }

//        if (!NetworkUtils.isOnline(mActivity)) {
//            if (CustomUtils.isCustomVer()) {
//                // do nothing 厂商定制版本暂不启用:无网自动到"我的"界面
//            } else {
//                index = MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY;
//            }
//        }

        LogUtils.d(TAG, "indexKey=" + index);
        switch (index) {
            case MainActivity.TAB_1_ACTIVITY:
                tab.check(R.id.tab_homepage);
                break;
            case MainActivity.TAB_2_ACTIVITY:
                tab.check(R.id.tab_trade);
                break;
            case MainActivity.TAB_3_ACTIVITY:
                tab.check(R.id.tab_watch);
                break;
            case MainActivity.TAB_4_ACTIVITY:
                tab.check(R.id.tab_mine);
                break;
        }
    }

    /**
     * 为首页提供当前tab区域，用于控制隐藏和显示
     *
     * @return
     */
    public RadioGroup getTab() {
        return mTab;
    }

}
