package com.example.chen.wanandroiddemo.main.activity.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 20:10
 */
public interface ArticleDetailContract {
    interface Presenter extends IPresenter<View> {
        boolean getNoImageMode();
        boolean getAutoCache();
    }

    interface View extends BaseView {

    }
}
