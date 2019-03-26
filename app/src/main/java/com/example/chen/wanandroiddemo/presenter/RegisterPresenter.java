package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.RegisterContract;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/25 22:24
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
