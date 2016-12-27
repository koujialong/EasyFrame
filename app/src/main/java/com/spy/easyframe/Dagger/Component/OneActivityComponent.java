package com.spy.easyframe.dagger.Component;

import com.spy.easyframe.dagger.ActivityScope;
import com.spy.easyframe.dagger.Module.OneActivityModule;
import com.spy.easyframe.module.OneModule.OneActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = OneActivityModule.class)
public interface OneActivityComponent {
    void inject(OneActivity oneActivity);
}
