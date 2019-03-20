package com.example.chen.wanandroiddemo.base.presenter;

import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 16:45
 */
public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T view;
    protected DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(T v) {
        view = v;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
