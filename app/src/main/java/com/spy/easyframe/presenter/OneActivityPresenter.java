package com.spy.easyframe.presenter;

import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.model.impl.IBaseModel;
import com.spy.easyframe.module.OneModule.impl.IOneActivity;
import com.spy.easyframe.presenter.impl.IGetDatePresenter;

import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2016/12/23.
 */
public class OneActivityPresenter implements IGetDatePresenter{
    private IBaseModel iBaseModel;
    private IOneActivity iOneActivity;
    protected Subscription subscription;

    public OneActivityPresenter(IOneActivity iOneActivity,Subscription subscription) {
        this.iOneActivity = iOneActivity;
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
        iOneActivity.showBanner((List<BannerModel.ItemBean>) object);
    }

    @Override
    public void getDateError() {

    }

}
