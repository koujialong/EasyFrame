package com.spy.easyframe.presenter.impl;

import rx.Subscription;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface IBasePresenter {
    void getDate(Subscription subscription,String commodityId);

    void getDateSucceed(Object object);

    void getDateError();
}
