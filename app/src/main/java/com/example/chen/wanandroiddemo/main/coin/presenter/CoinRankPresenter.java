package com.example.chen.wanandroiddemo.main.coin.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.coin.contract.CoinRankContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author chenshuaiyu
 */
public class CoinRankPresenter extends BasePresenter<CoinRankContract.View> implements CoinRankContract.Presenter {

    public CoinRankPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCoinRanks(int page) {
        addSubcriber(
                mDataManager.getCoinRanks(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(coinRanksBaseResponse -> {
                            mView.showCoinRanks(coinRanksBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
