package com.example.chen.wanandroiddemo.main.coin.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.coin.contract.CoinContract;

/**
 * @author chenshuaiyu
 */
public class CoinPresenter extends BasePresenter<CoinContract.View> implements CoinContract.Presenter {

    public CoinPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
