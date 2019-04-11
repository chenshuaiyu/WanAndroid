package com.example.chen.wanandroiddemo.presenter.search;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/23 9:57
 */
public class SearchArticlesPresenter extends BasePresenter<SearchArticlesContract.View> implements SearchArticlesContract.Presenter {
    @Inject
    public SearchArticlesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSearchArticles(int page, String key) {
        mDataManager.getSearchArticles(page, key)
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<Articles> articlesBaseResponse) {
                        mView.showSearchArticles(articlesBaseResponse.getData().getDatas());
                        mView.showNormalView();
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
