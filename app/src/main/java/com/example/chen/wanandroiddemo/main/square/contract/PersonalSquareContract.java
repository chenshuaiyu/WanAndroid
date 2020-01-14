package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;

public interface PersonalSquareContract {
    interface Presenter extends IPresenter<View> {
        void getPersonalSquare(int id, int page);
    }

    interface View extends BaseView {
        void showCoinInfo(SquareShareArticles.Coininfo coininfo);
        void showPersonalSquare(SquareShareArticles squareShareArticles);
    }
}
