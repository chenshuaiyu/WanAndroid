package com.example.chen.wanandroiddemo.base.presenter;


import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/11 21:34
 */
public interface IPresenter<T extends BaseView> {
    void attachView(T v);

    void detachView();
}
