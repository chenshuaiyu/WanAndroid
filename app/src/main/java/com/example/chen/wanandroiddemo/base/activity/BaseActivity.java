package com.example.chen.wanandroiddemo.base.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.NetUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 16:18
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements BaseView {

    private Unbinder mUnbinder;

    @Inject
    protected T presenter;

    /**
     * 提供布局Id
     *
     * @return 布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 依赖注入
     */
    protected abstract void inject();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        inject();
        presenter.attachView(this);
        initView();
        initData();
    }

    @Override
    public void recreate() {
        super.recreate();
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void useNightMode(boolean isNightMode) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        recreate();
    }

    @Override
    public void showNetChangeTips() {
        DataManager dataManager = WanAndroidApp.getInstance().getAppComponent().getDataManager();
        String state = NetUtils.getNetworkType();
        if (dataManager.getNetState().equals(Constants.NO_NETWORK)) {
            //reLoad();
        }
        dataManager.setNetState(state);
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }
}
