package com.spy.easyframe.dagger.Module;

import android.app.Activity;

import com.spy.easyframe.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/26.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity activity(){
        return this.activity;
    }
}
