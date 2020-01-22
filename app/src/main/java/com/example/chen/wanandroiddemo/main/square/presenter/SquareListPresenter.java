package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.SquareListContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author chenshuaiyu
 */
public class SquareListPresenter extends BasePresenter<SquareListContract.View> implements SquareListContract.Presenter {
    public SquareListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSquareList(int page) {
        addSubcriber(
                mDataManager.getSquareList(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(squareArticlesBaseResponse -> {
                            mView.showSquareList(squareArticlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void collectArticle(int id, int position) {
        addSubcriber(
                mDataManager.collectArticle(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCollectResult(baseResponse.getErrorCode() == 0, position)
                                , Throwable::printStackTrace)
        );
    }

    @Override
    public void cancelCollectArticle(int id, int position) {
        addSubcriber(
                mDataManager.cancelCollect(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCancelCollectResult(baseResponse.getErrorCode() == 0, position)
                                , Throwable::printStackTrace)
        );
    }
}
