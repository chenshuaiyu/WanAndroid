package com.example.chen.wanandroiddemo.main.collection.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
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
                mDataManager.getCollectedArticles(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showCollectedArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void cancelCollect(int id, int originId) {
        addSubcriber(
                mDataManager.cancelCollect(id, originId)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE)
                                , Throwable::printStackTrace)
        );
    }
}
