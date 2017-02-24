package com.spy.easyframe.presenter;

import com.spy.easyframe.model.TickChartModel;
import com.spy.easyframe.module.BaseImpl.ITickChartView;
import com.spy.easyframe.network.NetWork;
import com.spy.easyframe.presenter.impl.IBasePresenter;
import com.spy.easyframe.util.LogUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/22.
 */
public class TickChartPersenter implements IBasePresenter{
    private ITickChartView iChartView;
    public TickChartPersenter(ITickChartView iChartView) {
        this.iChartView=iChartView;
    }

    @Override
    public void getDate(Subscription subscription,String commodityId) {
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription= NetWork.getChartApi()
                .getTickChart(commodityId, "ctrade1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getDateSucceed(Object object) {

    }

    @Override
    public void getDateError() {

    }

    Observer<TickChartModel> observer=new Observer<TickChartModel>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtils.e("TAG",e.toString());
        }

        @Override
        public void onNext(TickChartModel model) {
            iChartView.showChart(model);
        }
    };
}
