package com.example.chen.wanandroiddemo.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 16:18
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T presenter;

    @LayoutRes
    protected abstract int getLayoutId();
    protected abstract void inject();
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        inject();
        presenter.attachView(this);
        initData();
    }
}
