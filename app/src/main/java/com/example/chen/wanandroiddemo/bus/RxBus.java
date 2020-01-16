package com.example.chen.wanandroiddemo.bus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 22:07
 */
public class RxBus {

    private final Subject<Object> mSubject;

    private RxBus() {
        mSubject = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 发送一个事件
     */
    public void post(Object o) {
        mSubject.onNext(o);
    }

    /**
     * 根据事件类型生成 Observable 对象
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mSubject.ofType(eventType);
    }

    private static class Holder {
        static final RxBus INSTANCE = new RxBus();
    }
}
