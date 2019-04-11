package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.RegisterContract;
import com.example.chen.wanandroiddemo.di.component.DaggerRegisterComponent;
import com.example.chen.wanandroiddemo.di.module.RegisterModule;
import com.example.chen.wanandroiddemo.presenter.activity.RegisterPresenter;
import butterknife.BindView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
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
    protected void initView() {
    }

    @Override
    protected void initData() {
        login.setOnClickListener(
                v -> {
                    jumpToLogin();
                }
        );
        register.setOnClickListener(
                v -> presenter.getRegisterData(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString())
        );
    }

    @Override
    public void showErrorMesssage(String error) {
        Toast.makeText(WanAndroidApp.getInstance(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessfulMesssage() {
        Toast.makeText(WanAndroidApp.getInstance(), "注册成功", Toast.LENGTH_SHORT).show();
        jumpToLogin();
    }

    private void jumpToLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
