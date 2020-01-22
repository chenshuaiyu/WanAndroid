package com.example.chen.wanandroiddemo.main.collection.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/26 22:32
 */
public interface CollectionContract {
    interface Presenter extends IPresenter<View> {
        void collectOutsideArticle(String title, String author, String link);

        void collectWebsite(String name, String link);
    }

    interface View extends BaseView {
        void showCollectResult(boolean success);
    }
}
