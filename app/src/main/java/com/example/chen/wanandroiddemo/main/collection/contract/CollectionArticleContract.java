package com.example.chen.wanandroiddemo.main.collection.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.CollectionArticle;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:57
 */
public interface CollectionArticleContract {
    interface Presenter extends IPresenter<View> {
        void getCollectedArticles(int page);

        void cancelCollect(int id, int originId);
    }

    interface View extends BaseView {
        void showCollectedArticles(List<CollectionArticle> articles);

        void showCollectResult(boolean success);
    }
}
