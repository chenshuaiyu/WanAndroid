package com.example.chen.wanandroiddemo.base.presenter;

import com.example.chen.wanandroiddemo.base.view.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 21:34
 */
public interface IPresenter<T extends BaseView> {

    /**
     * 绑定View
     *
     * @param v view
     */
    void attachView(T v);

    /**
     * 解除View
     */
    void detachView();

    /**
     * 管理订阅事件生命周期
     *
     * @param disposable
     */
    void addSubcriber(Disposable disposable);

    /**
     * 订阅事件
     */
    void subscribeEvent();

}
