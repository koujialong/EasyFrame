package com.spy.easyframe.presenter;

import com.spy.easyframe.model.LiveListModel;
import com.spy.easyframe.model.impl.IBaseModel;
import com.spy.easyframe.module.impl.ILiveListView;
import com.spy.easyframe.presenter.impl.ILiveListPresernter;

import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2016/12/24.
 */

public class LiveListPresenter implements ILiveListPresernter{
    private ILiveListView iLiveListView;
    private IBaseModel iBaseModel;
    private Subscription subscription;

    public LiveListPresenter(ILiveListView iLiveListView, Subscription subscription) {
        this.iLiveListView = iLiveListView;
        this.subscription = subscription;
        iBaseModel=new LiveListModel(this);
    }

    @Override
    public void getDate(Object... args) {
        iBaseModel.sendRequestToServer(subscription);
    }

    @Override
    public void getDateSucceed(Object object) {
        iLiveListView.showLiveList((List<LiveListModel.ItemBean.TeamListBean>) object);
    }

    @Override
    public void getDateError() {

    }
}
