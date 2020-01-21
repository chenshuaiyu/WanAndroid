package com.example.chen.wanandroiddemo.main.collection.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionWebsiteContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:58
 */
public class CollectionWebsitePresenter extends BasePresenter<CollectionWebsiteContract.View> implements CollectionWebsiteContract.Presenter {

    public CollectionWebsitePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCollectedWebsites() {
        addSubcriber(
                mDataManager.getCollectedWebsites()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(collectionWebsiteBaseResponse -> {
                            mView.showCollectedWebsites(collectionWebsiteBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
