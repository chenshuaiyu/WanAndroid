package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/26 22:32
 */
public interface CollectionContract {
    interface Presenter extends IPresenter<View> {

    }

    interface View extends BaseView {

    }
}
