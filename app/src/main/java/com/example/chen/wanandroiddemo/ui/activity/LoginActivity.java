package com.example.chen.wanandroiddemo.ui.activity;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.LoginContract;
import com.example.chen.wanandroiddemo.di.component.DaggerLoginComponent;
import com.example.chen.wanandroiddemo.di.module.LoginActivityModule;
import com.example.chen.wanandroiddemo.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject() {
        DaggerLoginComponent.builder().loginActivityModule(new LoginActivityModule()).build().inject(this);
    }

    @Override
    protected void initData() {

    }
}
