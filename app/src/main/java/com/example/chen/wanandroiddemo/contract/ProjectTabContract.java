package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 8:48
 */
public interface ProjectTabContract {
    interface Presenter extends IPresenter<View> {
        void getProjectTabArticles(int page, int id);
    }

    interface View extends BaseView {
        void showProjectTabArticles(List<Article> projectTabArticles);
    }
}
