package com.example.chen.wanandroiddemo.main.wx.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.wx.contract.WXTabContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/20 20:27
 */
public class WXTabPresenter extends BasePresenter<WXTabContract.View> implements WXTabContract.Presenter {

    public WXTabPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWXTabArticles(int id, int page) {
        mDataManager.getWXTabArticles(id, page)
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<Articles> wxTabArticlesBaseResponse) {
                        mView.showWXTabArticles(wxTabArticlesBaseResponse.getData().getDatas());
                        mView.showContentView();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getWXTabSearchArticles(int id, int page, String k) {
        mDataManager.getWxTabSearchArticles(id, page, k)
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<Articles> articlesBaseResponse) {
                        mView.showWXTabSearchArticles(articlesBaseResponse.getData().getDatas());
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
