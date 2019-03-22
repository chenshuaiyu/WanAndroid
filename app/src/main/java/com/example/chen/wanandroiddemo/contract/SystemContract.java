package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.System;

import java.util.List;


/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 14:20
 */
public interface SystemContract {
    interface Presenter extends IPresenter<View> {
        void getSystem();
    }

    interface View extends BaseView {
        void showSystem(List<System> systemList);
    }
}
