package com.example.chen.wanandroiddemo.main.search.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.search.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

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

    @Override
    public void collectArticle(int id, int position) {
        addSubcriber(
                mDataManager.collectArticle(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE, position)
                                , Throwable::printStackTrace)
        );
    }

    @Override
    public void cancelCollectArticle(int id, int position) {
        addSubcriber(
                mDataManager.cancelCollect(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCancelCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE, position)
                                , Throwable::printStackTrace)
        );
    }
}
