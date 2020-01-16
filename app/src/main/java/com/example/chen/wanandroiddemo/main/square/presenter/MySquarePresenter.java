package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.MySquareContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author chenshuaiyu
 */
public class MySquarePresenter extends BasePresenter<MySquareContract.View> implements MySquareContract.Presenter {
    public MySquarePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getMySquare(int page) {
        addSubcriber(
                mDataManager.getMySquare(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(squareShareArticlesBaseResponse -> {
                            mView.showMySquare(squareShareArticlesBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void shareArticle(String title, String link) {
        addSubcriber(
                mDataManager.shareArticle(title, link)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> {
                            if (baseResponse.getErrorCode() == 0) {
                                mView.showShareSuccess();
                            } else {
                                mView.showShareFail();
                            }
                        }, Throwable::printStackTrace)
        );
    }
}
