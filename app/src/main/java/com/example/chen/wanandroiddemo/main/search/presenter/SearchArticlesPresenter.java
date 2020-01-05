package com.example.chen.wanandroiddemo.main.search.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.search.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtils;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/23 9:57
 */
public class SearchArticlesPresenter extends BasePresenter<SearchArticlesContract.View> implements SearchArticlesContract.Presenter {

    public SearchArticlesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSearchArticles(int page, String key) {
        addSubcriber(
                mDataManager.getSearchArticles(page, key)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showSearchArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
