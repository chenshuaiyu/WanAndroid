package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 21:33
 */
public interface SettingsContract {
    interface Presenter extends IPresenter<View> {
        void setNightMode(boolean isNightMode);
        void getNightMode();
    }

    interface View extends BaseView {
        void showNightMode(boolean isNightMode);
    }
}
