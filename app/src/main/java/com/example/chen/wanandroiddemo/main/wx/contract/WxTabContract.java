package com.example.chen.wanandroiddemo.main.wx.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/20 20:27
 */
public interface WxTabContract {
    interface Presenter extends IPresenter<View> {
        void getWxTabArticles(int id, int page);
        void getWxTabSearchArticles(int id, int page, String k);
        void collectArticle(int id, int position);
        void cancelCollectArticle(int id, int position);
    }

    interface View extends BaseView {
        void showWxTabArticles(List<Article> wxTabArticles);
        void showWxTabSearchArticles(List<Article> wxTabArticles);
        void showCollectResult(boolean success, int position);
        void showCancelCollectResult(boolean success, int position);
    }
}
