package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.SquareListContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

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
                            mView.showSquareList(squareArticlesBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
