package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.WXTabArticle;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/20 20:27
 */
public interface WXTabContract {
    interface Presenter extends IPresenter<View> {
        void getWXTabArticles(int id, int page);
    }

    interface View extends BaseView {
        void showWXTabArticles(List<WXTabArticle> wxTabArticles);
    }
}
