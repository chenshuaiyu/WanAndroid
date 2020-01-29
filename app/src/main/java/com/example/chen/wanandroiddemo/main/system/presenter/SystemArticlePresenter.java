package com.example.chen.wanandroiddemo.main.system.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 20:16
 */
public class SystemArticlePresenter extends BasePresenter<SystemArticleContract.View> implements SystemArticleContract.Presenter {

    public SystemArticlePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSystemArticles(int page, int cid) {
        addSubcriber(
                mDataManager.getSystemArticles(page, cid)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showSystemArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
