package com.example.chen.wanandroiddemo.main.square.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.SquareContract;

/**
 * @author chenshuaiyu
 */
public class SquarePresenter extends BasePresenter<SquareContract.View> implements SquareContract.Presenter {
    public SquarePresenter(DataManager dataManager) {
        super(dataManager);
    }
}
