package com.spy.easyframe.model.impl;

import rx.Subscription;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface IBaseModel {
    //请求数据
    void sendRequestToServer(Subscription subscription);
    //设置请求方式
    void setMethod(int method);
    //取消请求
    void cancelRequest();
    //设置参数
    void setParam(Object ... args);
}
