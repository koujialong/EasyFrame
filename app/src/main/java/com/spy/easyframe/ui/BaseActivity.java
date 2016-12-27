/*
 * BaseActivity.java
 * classes : com.ledu.ledubuyer.ui.BaseActivity
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-5-28 下午7:46:05
 */
package com.spy.easyframe.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.Window;

import com.spy.easyframe.util.DisplayUtils;
import com.spy.easyframe.util.LogUtils;

import java.util.List;
import java.util.Stack;

import rx.Subscription;


/**
 * BaseActivity
 */
public abstract class BaseActivity extends FragmentActivity {
    /**
     * TAG
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    private static Stack<BaseActivity> sActivities = new Stack<BaseActivity>();

    private boolean mActivityPaused;
    private int downX=0;
    private int moveX=0;
    private int upX=0;
    private long downTime=0;
    private long upTime=0;
    public Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addActivity(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        injectComponent();//Dagger2绑定
//        initData();
//        initListener();
        //切换动画
//        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_exit_from_right);
    }

    protected abstract  void initData();

    protected abstract void initListener();

    protected abstract void injectComponent();

    @Override
    protected void onResume() {
        super.onResume();
        mActivityPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityPaused = true;
        LogUtils.d(TAG, "onPause");
    }

    /**
     * 判断Activity是否Paused
     *
     * @return
     */
    public boolean isActivityPaused() {
        return mActivityPaused;
    }

    @Override
    protected void onDestroy() {
        removeActivity(this);
        super.onDestroy();
        // 取消网络监听
        //HttpTaskManager.cancelAllRequests(getActivityTag());
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    /**
     * 界面Activity入栈
     *
     * @param activity
     */
    private static void addActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        sActivities.push(activity);
    }

    /**
     * 获取acitivty栈
     */
    public static Stack<BaseActivity> getActivityStack() {
        return sActivities;
    }

    /**
     * 获取栈顶界面Activity
     */
    public static BaseActivity getTopActivity() {
        if (sActivities.empty()) {
            return null;
        } else {
            return sActivities.peek();
        }
    }

    private static void removeActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }

        if (sActivities.contains(activity)) {
            sActivities.remove(activity);
        }
    }

    public static void closeApplication() {
        if (!sActivities.empty()) {
            for (int i = 0; i < sActivities.size(); i++) {
                BaseActivity activity=sActivities.get(i);
                if (activity != null && !activity.isFinishing())
                    activity.finish();
            }
            sActivities.clear();
        }

    }




    public static void closeApplicationWithOutLogin() {
        if (!sActivities.empty()) {
            for (int i = 0; i < sActivities.size(); i++) {
                BaseActivity activity=sActivities.get(i);
                if (activity != null && !activity.isFinishing()) {
//                    if (activity instanceof LoginActivity) {
//                        continue;
//                    }

                    activity.finish();
                    sActivities.remove(i);
                }
            }
        }
    }


    public static void refreshTopActivity(BaseActivity activity) {
        removeActivity(activity);
        addActivity(activity);
    }

    public static void addActivity() {

    }


    /**
     * 设置http request请求tag
     *
     * @return create at 2014-3-31 上午10:05:45
     * @author xiangyutian
     */
    public abstract String getActivityTag();


    /**
     * 判断程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction()== MotionEvent.ACTION_DOWN){
            downX= (int) event.getRawX();
            downTime= System.currentTimeMillis();
            if (downX<50){
                return true;
            }else {
                return super.dispatchTouchEvent(event);
            }
        }else if (event.getAction()== MotionEvent.ACTION_MOVE){
            moveX= (int) event.getRawX();
            if (downX<50){
                return true;
            }else {
                return super.dispatchTouchEvent(event);
            }
        }else if (event.getAction()== MotionEvent.ACTION_UP){
            upX= (int) event.getRawX();
            upTime= System.currentTimeMillis();
            if (downX<50&&downX!=0){
                if (moveX-downX>= DisplayUtils.getScreenWidth(this)/3&&upTime-downTime<500){
                    if (!sActivities.empty()) {
                        for (BaseActivity activity : sActivities) {
                            if (activity != null && !activity.isFinishing()) {
                                    finish();
                            }
                        }
                    }
                    return true;
                }else {
                    return super.dispatchTouchEvent(event);
                }
            }else {
                return super.dispatchTouchEvent(event);
            }
        }else {
            return super.dispatchTouchEvent(event);
        }
    }

//    /**
//    * 获得activity的component
//    */
//    protected AppComponent getActivityComponent(){
//        return DaggerAppComponent.builder()
//                .appModule(new ActivityModule(this))
//                .build();
//    }

}
