package com.example.chen.wanandroiddemo.ui.activity;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.di.component.DaggerSettingsComponent;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.presenter.activity.SettingsPresenter;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void inject() {
        DaggerSettingsComponent.builder().settingsModule(new SettingsModule()).build().inject(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }
}
