package com.example.chen.wanandroiddemo.presenter.activity;

import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/26 21:33
 */
public class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {
    @Inject
    public SettingsPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
