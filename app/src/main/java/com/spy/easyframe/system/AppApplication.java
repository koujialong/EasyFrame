package com.spy.easyframe.system;

import android.app.Application;

import com.spy.easyframe.dagger.Component.AppComponent;
import com.spy.easyframe.dagger.Module.AppModule;
import com.spy.easyframe.dagger.Component.DaggerAppComponent;

/**
 * Created by Administrator on 2016/12/26.
 */
public class AppApplication extends Application{
    private static AppApplication instance;
    private static AppComponent appComponent;

    public static AppApplication getInstance() {
        return instance;
    }

    public AppApplication() {
        instance=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(){
        return appComponent;
    }


}
