package com.example.chen.wanandroiddemo.main.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.LoginContract;
import com.example.chen.wanandroiddemo.main.activity.presenter.LoginPresenter;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.statelayout_lib.StateLayoutManager;

import butterknife.BindView;

public class LoginActivity extends BaseLoadActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText mUsernameEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;
    @BindView(R.id.btn_register)
    Button mRegisterBtn;
    @BindView(R.id.iv_close)
    ImageView mCloseIv;

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.activity_login)
                .setOnReLoadListener(() -> showContentView())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRegisterBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
        mLoginBtn.setOnClickListener(v ->
                mPresenter.getLoginData(mUsernameEt.getText().toString(), mPasswordEt.getText().toString())
        );
        mCloseIv.setOnClickListener(v -> finish());
    }

    @Override
    public void showErrorMesssage(String error) {
        ToastUtil.toast(error);
    }

    @Override
    public void showSuccessfulMesssage() {
        ToastUtil.toast(R.string.login_success);
        finish();
    }
}
