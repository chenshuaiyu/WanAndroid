package com.example.chen.wanandroiddemo.main.activity.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.SettingsContract;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 21:33
 */
public class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {

    public SettingsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void subscribeEvent() {
        super.subscribeEvent();
    }

    @Override
    public void setNightMode(boolean isNightMode) {
        mDataManager.setNightMode(isNightMode);
    }

    @Override
    public void getNightMode() {
        mView.showNightMode(mDataManager.getNightMode());
    }

    @Override
    public void setNoImageMode(boolean isNoImageMode) {
        mDataManager.setNoImageMode(isNoImageMode);
    }

    @Override
    public void getNoImageMode() {
        mView.showNoImageMode(mDataManager.getNoImageMode());
    }

    @Override
    public void setAutoCache(boolean isAutoCache) {
        mDataManager.setAutoCache(isAutoCache);
    }

    @Override
    public void getAutoCache() {
        mView.showAutoCache(mDataManager.getAutoCache());
    }
}
