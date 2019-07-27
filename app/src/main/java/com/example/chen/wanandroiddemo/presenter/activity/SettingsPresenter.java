package com.example.chen.wanandroiddemo.presenter.activity;

import android.util.Log;

import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.bus.RxBus;
import com.example.chen.wanandroiddemo.bus.event.NightModeEvent;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 21:33
 */
public class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {
    @Inject
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
}
