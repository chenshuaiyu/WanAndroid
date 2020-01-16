package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;

public interface MySquareContract {
    interface Presenter extends IPresenter<View> {
        void getMySquare(int page);
        void shareArticle(String title, String link);
    }

    interface View extends BaseView {
        void showMySquare(SquareShareArticles squareShareArticles);
        void showShareSuccess();
        void showShareFail();
    }
}
