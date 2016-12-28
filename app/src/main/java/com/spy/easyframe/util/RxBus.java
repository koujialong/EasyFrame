package com.spy.easyframe.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/12/28.
 */
public class RxBus {
    private static volatile RxBus instance;

    private final Subject<Object,Object> bus;
    //PublishSubject只会把订阅发生的时间点之后来自原始的Observeable的数据发送给观察者
    public RxBus() {
        bus=new SerializedSubject<>(PublishSubject.create());
    }

    //单例的RxBus
    public static RxBus getInstance(){
        if (instance==null){
            synchronized (RxBus.class){
                if (instance==null){
                    instance=new RxBus();
                }
            }
        }
        return instance;
    }

    //发送一个新的事件
    public void post(Object o){
        bus.onNext(o);
    }

    //根据专递的eventType类型返回特定类（eventType）的被观察者
    public <T>Observable<T> toObservable(Class<T> eventType){
        return bus.ofType(eventType);
    }
}
