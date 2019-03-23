package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<Articles> articlesBaseResponse) {
                        view.showSearchArticles(articlesBaseResponse.getData().getDatas());
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
