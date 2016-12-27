package com.spy.easyframe.dagger.Component;

import android.app.Activity;

import com.spy.easyframe.dagger.ActivityScope;
import com.spy.easyframe.dagger.Module.ActivityModule;
import com.spy.easyframe.module.TwoModule.TwoActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/26.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

    void inject(TwoActivity oneActivity);
}
