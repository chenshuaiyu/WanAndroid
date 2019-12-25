package com.example.chen.wanandroiddemo.main.system.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 20:16
 */
public interface SystemArticleContract {
    interface Presenter extends IPresenter<View> {
        void getSystemArticles(int page, int cid);
    }

    interface View extends BaseView {
        void showSystemArticles(List<Article> articles);
    }
}
