package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/17 16:09
 */
public interface HomeContract {
    interface Presenter extends IPresenter<View>{
        void getBanner();
        void getArticles(int page);
    }

    interface View extends BaseView{
        void showBanner(List<Banner> banners);
        void showArticles(List<Article> articles);
    }
}
