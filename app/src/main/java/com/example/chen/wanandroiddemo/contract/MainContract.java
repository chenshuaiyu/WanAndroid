package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 17:14
 */
public interface MainContract {

    interface Presenter extends IPresenter<View> {
        void setLoginUser();
        void logout();
    }

    interface View extends BaseView {
        void showLoginUser(String account);
        void resetLoginUser();
        void setLogoutVisibility(boolean visiable);
        void showLogoutSucceed();
        void showLogoutFailed();
    }
}
