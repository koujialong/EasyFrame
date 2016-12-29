package com.spy.easyframe.model;

import android.util.Log;

import com.spy.easyframe.model.impl.IBaseModel;
import com.spy.easyframe.network.NetWork;
import com.spy.easyframe.presenter.impl.IBannerPresenter;

import java.io.Serializable;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/22.
 */
public class BannerModel implements IBaseModel,Serializable{

    /**
     * code : 0
     * msg : success
     * startIndex : 0
     * total : 0
     * pageSize : 30
     * curPage : 0
     * items : []
     * item : [{"id":2,"date":"2016-01-16 17:07:34","img_url":"http://wp.500win.cn/ctrade/img/ad.png","action":"url","url":"http://www.baidu.com","title":""},{"id":1,"date":"2016-01-16 17:07:08","img_url":"http://wp.500win.cn/ctrade/img/ad.png","action":"none","url":"http://500win.cn","title":""}]
     * totalPage : 0
     */

    private int code;
    private String msg;
    private int startIndex;
    private int total;
    private int pageSize;
    private int curPage;
    private int totalPage;
    private List<?> items;
    /**
     * id : 2
     * date : 2016-01-16 17:07:34
     * img_url : http://wp.500win.cn/ctrade/img/ad.png
     * action : url
     * url : http://www.baidu.com
     * title :
     */

    private List<ItemBean> item;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        private int id;
        private String date;
        private String img_url;
        private String action;
        private String url;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public BannerModel() {
    }

    @Override
    public void sendRequestToServer(Subscription subscription) {
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        subscription= NetWork.getBaseApi()
                .getBannerList(page, limit, type)
                .map(new Func1<BannerModel, List<ItemBean>>() {
                    @Override
                    public List<ItemBean> call(BannerModel bannerModel) {
                        return bannerModel.getItem();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void setMethod(int method) {

    }

    @Override
    public void cancelRequest() {

    }

    private int page,limit;
    private String type;
    private List<BannerModel.ItemBean> itemBeens;
    private IBannerPresenter iGetDatePresenter;
    @Override
    public void setParam(Object ... args){
        this.page= (int) args[0];
        this.limit= (int) args[1];
        this.type= (String) args[2];
    }

    public BannerModel(IBannerPresenter iGetDatePresenter) {
        this.iGetDatePresenter = iGetDatePresenter;
    }

    Observer<List<BannerModel.ItemBean>> observer=new Observer<List<BannerModel.ItemBean>>() {
        @Override
        public void onCompleted() {
            iGetDatePresenter.getDateSucceed(itemBeens);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("TAG",e.getMessage());
        }

        @Override
        public void onNext(List<BannerModel.ItemBean> list) {
            itemBeens=list;
        }
    };
}
