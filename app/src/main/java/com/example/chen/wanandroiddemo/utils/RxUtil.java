package com.example.chen.wanandroiddemo.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/29 19:36
 */
public class RxUtil {

    //切换线程
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
