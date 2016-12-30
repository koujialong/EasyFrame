package com.spy.easyframe.action.ui;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.spy.easyframe.action.core.ActionManager;
import com.spy.easyframe.cache.disk.DiskCacheManager;
import com.spy.easyframe.ui.BaseActivity;
import com.spy.easyframe.ui.MainActivity;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.StringUtils;

import java.util.List;

/**
 * Created by xiangyutian on 16/4/9.
 */
public class ActionEntryActivity extends BaseActivity {

    /**
     * tag
     */
    private static final String TAG = ActionManager.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String time = DiskCacheManager.getInstance(this).getAsString(DiskCacheManager.ACTION_TIME);
            if (TextUtils.isEmpty(time)){
                time="0";
            }
            long tm=Long.parseLong(time);
            String dataPath = intent.getDataString();
            if (StringUtils.isNotBlank(dataPath)) {
                if (System.currentTimeMillis()-tm>1000) {
                    DiskCacheManager.getInstance(this).put(DiskCacheManager.ACTION_TIME,System.currentTimeMillis()+"");
                    if (appMainActivityIsRunning()) {
                        //startActivity(IntentTools.getMainActivity(QilinEntryActivity.this,dataPath));
                        appToTopStack();
                        ActionManager manager = new ActionManager(MainActivity.getInstance(), dataPath);
                        if (manager.isVailidAction()) {
                            manager.processAction();
                        }
                    } else {
                        LogUtils.d(TAG, "dataPath =========  " + dataPath);
//                    BaseActivity.closeApplication();
//                    moveTaskToBack(true);
                        Intent mainIntent = new Intent(this, MainActivity.class);
                        mainIntent.putExtra("datePath",dataPath);
                        startActivity(mainIntent);
                    }
                }
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void injectComponent() {

    }

    @Override
    public String getActivityTag() {
        return TAG;
    }

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


    public boolean appMainActivityIsRunning(){
        ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(getPackageName()) &&
                    info.baseActivity.getShortClassName().equals(".ui.MainActivity")) {
                return true;
            }
        }
        return false;
    }

    public void appToTopStack() {
        ActivityManager manager = (ActivityManager)this
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> task_info = manager
                .getRunningTasks(20);
        String className = "";

        for (int i = 0; i < task_info.size(); i++)
        {

            if (getPackageName().equals(task_info
                    .get(i).topActivity.getPackageName())){

                className = task_info.get(i).topActivity
                        .getClassName();

                //这里是指从后台返回到前台  前两个的是关键
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                try {
                    intent.setComponent(new ComponentName(
                            this, Class.forName(className)));
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                            | Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    this.startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
