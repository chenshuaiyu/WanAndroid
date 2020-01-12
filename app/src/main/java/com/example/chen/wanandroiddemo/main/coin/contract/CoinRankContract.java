package com.example.chen.wanandroiddemo.main.coin.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.CoinRanks;

public interface CoinRankContract {
    interface Presenter extends IPresenter<View> {
        void getCoinRanks(int page);
    }

    interface View extends BaseView {
        void showCoinRanks(CoinRanks coinRanks);
    }
}
