package com.example.chen.wanandroiddemo.main.activity.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 11:48
 */
public interface LoginContract {
    interface Presenter extends IPresenter<View> {
        void getLoginData(String useranme, String password);
    }

    interface View extends BaseView {
        void showLoginResult(boolean success, String errorMsg);
    }
}
