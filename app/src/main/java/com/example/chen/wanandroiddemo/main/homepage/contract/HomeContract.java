package com.example.chen.wanandroiddemo.main.homepage.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/17 16:09
 */
public interface HomeContract {
    interface Presenter extends IPresenter<View> {
        void getBanner();
        void getArticles(int page);
        void collectArticle(int id, int position);
        void cancelCollectArticle(int id, int position);
    }

    interface View extends BaseView {
        void showBanner(List<Banner> banners);
        void showArticles(List<Article> articles);
        void showCollectResult(boolean success, int position);
        void showCancelCollectResult(boolean success, int position);
    }
}
