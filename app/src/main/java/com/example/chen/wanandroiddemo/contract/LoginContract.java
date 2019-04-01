package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 11:48
 */
public interface LoginContract {
    interface Presenter extends IPresenter<View> {
        void getLoginData(String useranme, String password);
    }

    interface View extends BaseView {
        void showErrorMesssage(String error);
        void showSuccessfulMesssage();
    }
}
