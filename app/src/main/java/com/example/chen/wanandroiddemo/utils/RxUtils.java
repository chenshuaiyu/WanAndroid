package com.example.chen.wanandroiddemo.utils;

import com.example.chen.wanandroiddemo.core.bean.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/29 19:36
 */
public class RxUtils {

    //切换线程
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
