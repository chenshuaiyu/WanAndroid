package com.example.chen.wanandroiddemo.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayout;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 16:18
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements BaseView {

    private Unbinder mUnbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initPresenter();
        init();
        mUnbinder = ButterKnife.bind(this);
    }

    private void initPresenter() {
        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    /**
     * 获取页面布局
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 获取Presenter
     *
     * @return
     */
    protected abstract T getPresenter();

    /**
     * 初始化StateLayout
     *
     * @return
     */
    protected void init() {
    }


    @Override
    public void showContentView() {
    }

    @Override
    public void showLoadingView() {
    }

    @Override
    public void showEmptyDataView() {
    }

    @Override
    public void showNetErrorView() {
    }

    @Override
    public void showErrorView() {
    }

    @Override
    public void reLoad() {
    }

    @Override
    public void useNightMode(boolean isNightMode) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void showNetChangeTips() {
        DataManager dataManager = DataManager.getInstance();
        String state = NetUtil.getNetworkType();
        if (dataManager.getNetState().equals(Constants.NO_NETWORK)) {
            //reLoad();
        }
        dataManager.setNetState(state);
        ToastUtil.toast(state);
    }
}
