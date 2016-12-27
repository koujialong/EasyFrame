package com.spy.easyframe.dagger.Module;

import android.app.Activity;

import com.spy.easyframe.module.BaseImpl.IBannerView;
import com.spy.easyframe.module.BaseImpl.ILiveListView;
import com.spy.easyframe.presenter.BannerPresenter;
import com.spy.easyframe.presenter.LiveListPresenter;

import dagger.Module;
import dagger.Provides;
import rx.Subscription;

/**
 * Created by Administrator on 2016/12/27.
 */
@Module
public class OneActivityModule {
    private final Activity activity;
    private Subscription subscription;

    public OneActivityModule(Activity activity, Subscription subscription) {
        this.activity = activity;
        this.subscription = subscription;
    }

    @Provides
    BannerPresenter getBannerPresenter(){
        return new BannerPresenter((IBannerView) activity,subscription);
    }

    @Provides
    LiveListPresenter getLiveListPresenter(){
        return new LiveListPresenter((ILiveListView) activity,subscription);
    }
}
