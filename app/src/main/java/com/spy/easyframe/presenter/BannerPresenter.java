package com.spy.easyframe.presenter;

import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.model.impl.IBaseModel;
import com.spy.easyframe.module.BaseImpl.IBannerView;
import com.spy.easyframe.presenter.impl.IBannerPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Administrator on 2016/12/23.
 */
public class BannerPresenter implements IBannerPresenter {
    private IBaseModel iBaseModel;
    private IBannerView iBannerView;
    protected Subscription subscription;

    @Inject
    public BannerPresenter(IBannerView iBannerView, Subscription subscription) {
        this.iBannerView = iBannerView;
        this.subscription=subscription;
        iBaseModel=new BannerModel(this);
    }

    @Override
    public void getDate(Object... args) {
        iBaseModel.setParam(args);
        iBaseModel.sendRequestToServer(subscription);
    }

    @Override
    public void getDateSucceed(Object object) {
        iBannerView.showBanner((List<BannerModel.ItemBean>) object);
    }

    @Override
    public void getDateError() {

    }

}
