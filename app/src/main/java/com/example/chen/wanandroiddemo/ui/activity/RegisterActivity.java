package com.example.chen.wanandroiddemo.ui.activity;

import android.widget.Button;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.RegisterContract;
import com.example.chen.wanandroiddemo.di.component.DaggerRegisterComponent;
import com.example.chen.wanandroiddemo.di.module.RegisterModule;
import com.example.chen.wanandroiddemo.presenter.RegisterPresenter;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.confirm_password)
    TextView confirmPassword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void inject() {
        DaggerRegisterComponent.builder().registerModule(new RegisterModule()).build().inject(this);
    }

    @Override
    protected void initData() {

    }

}
