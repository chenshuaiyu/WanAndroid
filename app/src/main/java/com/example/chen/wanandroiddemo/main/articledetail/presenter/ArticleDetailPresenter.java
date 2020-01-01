package com.example.chen.wanandroiddemo.main.articledetail.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.articledetail.contract.ArticleDetailContract;
import com.example.chen.wanandroiddemo.core.DataManager;


/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 20:09
 */
public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    public ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public boolean getNoImageMode() {
        return mDataManager.getNoImageMode();
    }

    @Override
    public boolean getAutoCache() {
        return mDataManager.getAutoCache();
    }
}
