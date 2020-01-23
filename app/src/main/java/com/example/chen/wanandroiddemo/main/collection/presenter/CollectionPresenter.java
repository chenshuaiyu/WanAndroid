package com.example.chen.wanandroiddemo.main.collection.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:53
 */
public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {

    public CollectionPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void collectOutsideArticle(String title, String author, String link) {
        addSubcriber(
                mDataManager.collectOutsideArticle(title, author, link)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE)
                                , Throwable::printStackTrace)
        );
    }

    @Override
    public void collectWebsite(String name, String link) {
        addSubcriber(
                mDataManager.collectWebsite(name, link)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse ->
                                        mView.showCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE)
                                , Throwable::printStackTrace)
        );
    }
}
