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
    private List<BannerModel.ItemBean> list;

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
        list=(List<BannerModel.ItemBean>) object;
        iBannerView.showBanner(list);
    }

    @Override
    public void getDateError() {

    }

    public String[] getImages(){
        if (list!=null) {
            String images[] = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                images[i] = list.get(i).getImg_url();
            }
            return images;
        }
        return null;
    }

}
