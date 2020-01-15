package com.example.chen.wanandroiddemo.main.coin.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.CoinRecords;

public interface MyCoinContract {
    interface Presenter extends IPresenter<View> {
        void getCoin();
        void getCoinRecords(int page);
        boolean getLoginStatus();
    }

    interface View extends BaseView {
        void showCoin(Coin coin);
        void showCoinRecords(CoinRecords coinRecords);
    }
}
