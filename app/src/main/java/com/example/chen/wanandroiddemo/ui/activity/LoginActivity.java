package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.LoginContract;
import com.example.chen.wanandroiddemo.di.component.DaggerLoginComponent;
import com.example.chen.wanandroiddemo.di.module.LoginActivityModule;
import com.example.chen.wanandroiddemo.presenter.activity.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
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
    protected void initView() {
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        register.setOnClickListener(
                v -> {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                }
        );
        login.setOnClickListener(
                v -> presenter.getLoginData(username.getText().toString(), password.getText().toString())
        );
    }

    @Override
    public void showErrorMesssage(String error) {
        Toast.makeText(WanAndroidApp.getInstance(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfulMesssage() {
        Toast.makeText(WanAndroidApp.getInstance(), "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
