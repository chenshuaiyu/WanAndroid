package com.example.chen.wanandroiddemo.main.collection.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionArticleContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:59
 */
public class CollectionArticlePresenter extends BasePresenter<CollectionArticleContract.View> implements CollectionArticleContract.Presenter {

    public CollectionArticlePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCollectedArticles(int page) {
        addSubcriber(
                mDataManager.getCollectedArtciles(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showCollectedArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
