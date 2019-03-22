package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 22:15
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
