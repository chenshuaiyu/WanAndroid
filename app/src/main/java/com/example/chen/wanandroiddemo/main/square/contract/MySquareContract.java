package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;

public interface MySquareContract {
    interface Presenter extends IPresenter<View> {
        void getMySquare(int page);
        void shareArticle(String title, String link);
        void collectArticle(int id, int position);
        void cancelCollectArticle(int id, int position);
        void deleteShareArticle(int id, int position);
    }

    interface View extends BaseView {
        void showMySquare(SquareShareArticles squareShareArticles);
        void showShareResult(boolean success);
        void showCollectResult(boolean success, int position);
        void showCancelCollectResult(boolean success, int position);
        void showDeleteResult(boolean success, int position);
    }
}
