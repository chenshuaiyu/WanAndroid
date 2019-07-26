package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/23 9:57
 */
public interface SearchArticlesContract {
    interface Presenter extends IPresenter<View> {
        void getSearchArticles(int page, String key);
    }

    interface View extends BaseView {
        void showSearchArticles(List<Article> articles);
    }
}
