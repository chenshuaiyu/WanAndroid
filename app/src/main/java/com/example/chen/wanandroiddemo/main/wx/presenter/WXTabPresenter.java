package com.example.chen.wanandroiddemo.main.wx.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.wx.contract.WXTabContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/20 20:27
 */
public class WXTabPresenter extends BasePresenter<WXTabContract.View> implements WXTabContract.Presenter {

    public WXTabPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWXTabArticles(int id, int page) {
        addSubcriber(mDataManager.getWXTabArticles(id, page)
                .compose(RxUtils.switchSchedulers())
                .subscribe(articlesBaseResponse -> {
                    mView.showWXTabArticles(articlesBaseResponse.getData().getDatas());
                    mView.showContentView();
                }, Throwable::printStackTrace)
        );
    }

    @Override
    public void getWXTabSearchArticles(int id, int page, String k) {
        addSubcriber(
                mDataManager.getWxTabSearchArticles(id, page, k)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> mView.showWXTabSearchArticles(articlesBaseResponse.getData().getDatas()),
                                Throwable::printStackTrace)
        );
    }
}
