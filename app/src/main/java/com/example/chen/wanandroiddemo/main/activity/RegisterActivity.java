package com.example.chen.wanandroiddemo.main.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.RegisterContract;
import com.example.chen.wanandroiddemo.main.activity.presenter.RegisterPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.statelayout_lib.StateLayoutManager;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class RegisterActivity extends BaseLoadActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.et_username)
    EditText mUsernameEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.et_confirm_password)
    EditText mConfirmPasswordEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;
    @BindView(R.id.btn_register)
    Button mRegisterBtn;
    @BindView(R.id.iv_close)
    ImageView mCloseIv;

    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.activity_register)
                .setOnReLoadListener(this::showContentView)
                .build();
    }

    @Override
    protected void initView() {

        mLoginBtn.setOnClickListener(
                v -> {
                    OpenActivityUtil.openLoginActivity(this);
                    finish();
                }
        );
        mRegisterBtn.setOnClickListener(
                v -> mPresenter.getRegisterData(mUsernameEt.getText().toString(), mPasswordEt.getText().toString(), mConfirmPasswordEt.getText().toString())
        );
        mCloseIv.setOnClickListener(v -> finish());
    }

    @Override
    public void showRegisterResult(boolean success, String errorMsg) {
        if (success) {
            ToastUtil.toast(R.string.register_success);
            OpenActivityUtil.openLoginActivity(this);
            finish();
        } else {
            ToastUtil.toast(errorMsg);
        }
    }
}
