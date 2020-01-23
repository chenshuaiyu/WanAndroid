package com.example.chen.wanandroiddemo.main.square.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;

/**
 * @author chenshuaiyu
 */
public interface PersonalSquareContract {
    interface Presenter extends IPresenter<View> {
        void getPersonalSquare(int id, int page);
        void collectArticle(int id, int position);
        void cancelCollectArticle(int id, int position);
    }

    interface View extends BaseView {
        void showCoinInfo(Coin coininfo);
        void showPersonalSquare(SquareShareArticles squareShareArticles);
        void showCollectResult(boolean success, int position);
        void showCancelCollectResult(boolean success, int position);
    }
}
