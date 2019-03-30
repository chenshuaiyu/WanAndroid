package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.LoginContract;
import com.example.chen.wanandroiddemo.di.component.DaggerLoginComponent;
import com.example.chen.wanandroiddemo.di.module.LoginActivityModule;
import com.example.chen.wanandroiddemo.presenter.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;

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
        register.setOnClickListener(
                v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );
    }
}
