package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/20 20:27
 */
public interface WXTabContract {
    interface Presenter extends IPresenter<View> {
        void getWXTabArticles(int id, int page);
        void getWXTabSearchArticles(int id, int page, String k);
    }

    interface View extends BaseView {
        void showWXTabArticles(List<Article> wxTabArticles);
        void showWXTabSearchArticles(List<Article> wxTabArticles);
    }
}
