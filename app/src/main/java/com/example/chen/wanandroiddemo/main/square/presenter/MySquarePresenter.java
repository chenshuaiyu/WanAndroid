package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
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
                        .subscribe(baseResponse -> mView.showShareResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE)
                                , Throwable::printStackTrace)
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

    @Override
    public void deleteShareArticle(int id, int position) {
        addSubcriber(
                mDataManager.deleteShareArticle(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse ->
                                        mView.showDeleteResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE, position)
                                , Throwable::printStackTrace)
        );
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }
}
