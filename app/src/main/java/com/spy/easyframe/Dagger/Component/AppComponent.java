package com.spy.easyframe.dagger.Component;

import android.content.Context;

import com.spy.easyframe.dagger.Module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/26.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
}
