package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.WXTabContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/20 20:27
 */
public class WXTabPresenter extends BasePresenter<WXTabContract.View> implements WXTabContract.Presenter {

    @Inject
    public WXTabPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getWXTabArticles(int id, int page) {
        mDataManager.getWXTabArticles(id, page)
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<Articles> wxTabArticlesBaseResponse) {
                        mView.showWXTabArticles(wxTabArticlesBaseResponse.getData().getDatas());
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
