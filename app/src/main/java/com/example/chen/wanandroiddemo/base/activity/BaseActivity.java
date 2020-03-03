package com.example.chen.wanandroiddemo.base.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.event.NetChangeEvent;
import com.example.chen.wanandroiddemo.event.NightModeEvent;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
     * 预留StateLayout初始化入口
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetChangeEvent(NetChangeEvent event) {
        DataManager dataManager = DataManager.getInstance();
        String state = NetUtil.getNetworkType();
        if (!dataManager.getNetState().equals(state)) {
            dataManager.setNetState(state);
            ToastUtil.toast(state);
//            reLoad();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNightModeEvent(NightModeEvent event) {
        if (event.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
