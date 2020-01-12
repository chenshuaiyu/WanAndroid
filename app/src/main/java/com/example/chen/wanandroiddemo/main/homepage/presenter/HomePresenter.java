package com.example.chen.wanandroiddemo.main.homepage.presenter;

import android.util.Log;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.homepage.contract.HomeContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/17 16:08
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getBanner() {
        addSubcriber(
                mDataManager.getBanner()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showBanner(listBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void getArticles(int page) {
        addSubcriber(mDataManager.getArticles(page)
                .compose(RxUtils.switchSchedulers())
                .subscribe(articlesBaseResponse -> {
                    mView.showArticles(articlesBaseResponse.getData().getDatas());
                    mView.showContentView();
                }, Throwable::printStackTrace)
        );
    }
}
