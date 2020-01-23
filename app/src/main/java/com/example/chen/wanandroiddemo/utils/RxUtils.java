package com.example.chen.wanandroiddemo.utils;

import com.example.chen.wanandroiddemo.core.bean.base.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/29 19:36
 */
public class RxUtils {

    /**
     * 切换线程
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 预处理，返回成功的结果
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleRequest() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        return createObservable(tBaseResponse.getData());
                    }
                });
            }
        };
    }

    /**
     * 创建成功的数据源
     */
    private static <T> Observable<T> createObservable(T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                if(t == null){
                    emitter.onError(new NullPointerException("Data is null."));
                    return;
                }
                emitter.onNext(t);
                emitter.onComplete();
            }
        });
    }
}
