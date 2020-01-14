package com.example.chen.wanandroiddemo.main.square.presenter;

import android.util.Log;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.PersonalSquareContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

public class PersonalSquarePresenter extends BasePresenter<PersonalSquareContract.View> implements PersonalSquareContract.Presenter {
    public PersonalSquarePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getPersonalSquare(int id, int page) {
        addSubcriber(
                mDataManager.getPersonalSquare(id, page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(squareShareArticlesBaseResponse -> {
                            mView.showCoinInfo(squareShareArticlesBaseResponse.getData().getCoinInfo());
                            mView.showPersonalSquare(squareShareArticlesBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
