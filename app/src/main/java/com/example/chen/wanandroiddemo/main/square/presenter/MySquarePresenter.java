package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.MySquareContract;

public class MySquarePresenter extends BasePresenter<MySquareContract.View> implements MySquareContract.Presenter {
    public MySquarePresenter(DataManager dataManager) {
        super(dataManager);
    }
}
