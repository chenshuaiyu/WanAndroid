package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.SquareArticles;

public interface SquareListContract {
    interface Presenter extends IPresenter<View> {
        void getSquareList(int page);
    }

    interface View extends BaseView {
        void showSquareList(SquareArticles squareArticles);
    }
}
