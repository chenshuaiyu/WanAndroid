package com.example.chen.wanandroiddemo.main.common.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

public interface CommonContract {
    interface Presenter extends IPresenter<View> {

    }

    interface View extends BaseView {

    }
}
