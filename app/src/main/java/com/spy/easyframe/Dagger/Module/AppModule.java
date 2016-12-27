package com.spy.easyframe.dagger.Module;

import android.content.Context;

import com.spy.easyframe.system.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/26.
 */
@Module
public class AppModule {
    private final AppApplication application;

    public AppModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return application;
    }
}
