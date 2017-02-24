package com.spy.easyframe.presenter;

import com.spy.easyframe.model.KLineModel;
import com.spy.easyframe.module.BaseImpl.IKLineChartView;
import com.spy.easyframe.network.NetWork;
import com.spy.easyframe.presenter.impl.IBasePresenter;
import com.spy.easyframe.util.LogUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/23.
 */
public class CandleStickChartPresenter implements IBasePresenter{
    private IKLineChartView ikLineChartView;

    public CandleStickChartPresenter(IKLineChartView ikLineChartView) {
        this.ikLineChartView = ikLineChartView;
    }

    @Override
    public void getDate(Subscription subscription, String commodityId) {
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription= NetWork.getChartApi()
                .getKLineChart("ctrade1","3","6","200","1","MA","MACD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KLineModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("TAG",e.getMessage());
                    }

                    @Override
                    public void onNext(KLineModel kLineModel) {
                        ikLineChartView.showChart(kLineModel);
                    }
                });
    }

    @Override
    public void getDateSucceed(Object object) {

    }

    @Override
    public void getDateError() {

    }
}
