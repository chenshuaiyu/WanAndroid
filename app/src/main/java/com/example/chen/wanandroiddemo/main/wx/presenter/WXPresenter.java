package com.example.chen.wanandroiddemo.main.wx.presenter;

import android.util.Log;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.wx.contract.WXContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 17:22
 */
public class WXPresenter extends BasePresenter<WXContract.View> implements WXContract.Presenter {

    public WXPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWXTab() {
        addSubcriber(
                mDataManager.getWXTab()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showTab(listBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
