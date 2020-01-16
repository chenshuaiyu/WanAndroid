package com.example.chen.wanandroiddemo.main.articledetail.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.main.articledetail.contract.ArticleContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * @author chenshuaiyu
 */
public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter {
    public ArticlePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void collectActicle(int id) {
        addSubcriber(
                mDataManager.collectArticle(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> {
                            if (baseResponse.getErrorCode() == 0) {
                                mView.showCollectSuccess();
                            } else {
                                mView.showCollectFail();
                            }
                        }, Throwable::printStackTrace)
        );
    }
}
