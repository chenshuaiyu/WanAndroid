package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.SquareArticle;

import java.util.List;

public interface SquareListContract {
    interface Presenter extends IPresenter<View> {
        void getSquareList(int page);
        void collectArticle(int id, int position);
        void cancelCollectArticle(int id, int position);
    }

    interface View extends BaseView {
        void showSquareList(List<SquareArticle> squareArticles);
        void showCollectResult(boolean success, int position);
        void showCancelCollectResult(boolean success, int position);
    }
}
