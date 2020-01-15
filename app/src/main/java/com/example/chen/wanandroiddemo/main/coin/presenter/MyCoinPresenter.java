package com.example.chen.wanandroiddemo.main.coin.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.coin.contract.MyCoinContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

public class MyCoinPresenter extends BasePresenter<MyCoinContract.View> implements MyCoinContract.Presenter {

    public MyCoinPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCoin() {
        addSubcriber(
                mDataManager.getCoin()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(coinBaseResponse -> {
                            mView.showCoin(coinBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void getCoinRecords(int page) {
        addSubcriber(
                mDataManager.getCoinRecords(page)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(coinRecordsBaseResponse -> {
                            mView.showCoinRecords(coinRecordsBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }
}
