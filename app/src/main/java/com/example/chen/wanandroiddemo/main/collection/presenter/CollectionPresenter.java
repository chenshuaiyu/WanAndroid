package com.example.chen.wanandroiddemo.main.collection.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionContract;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:53
 */
public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {

    public CollectionPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
