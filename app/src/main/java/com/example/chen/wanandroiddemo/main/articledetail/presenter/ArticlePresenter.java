package com.example.chen.wanandroiddemo.main.articledetail.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.articledetail.contract.ArticleContract;

/**
 * @author chenshuaiyu
 */
public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter {

    public ArticlePresenter(DataManager dataManager) {
        super(dataManager);
    }

}
