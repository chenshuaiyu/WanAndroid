package com.example.chen.wanandroiddemo.main.wx.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.wx.contract.WxTabContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/20 20:27
 */
public class WxTabPresenter extends BasePresenter<WxTabContract.View> implements WxTabContract.Presenter {

    public WxTabPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWxTabArticles(int id, int page) {
        addSubcriber(mDataManager.getWxTabArticles(id, page)
                .compose(RxUtils.switchSchedulers())
                .subscribe(articlesBaseResponse -> {
                    mView.showWxTabArticles(articlesBaseResponse.getData().getDatas());
                    mView.showContentView();
                }, Throwable::printStackTrace)
        );
    }

    @Override
    public void getWxTabSearchArticles(int id, int page, String k) {
        addSubcriber(
                mDataManager.getWxTabSearchArticles(id, page, k)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> mView.showWxTabSearchArticles(articlesBaseResponse.getData().getDatas()),
                                Throwable::printStackTrace)
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
