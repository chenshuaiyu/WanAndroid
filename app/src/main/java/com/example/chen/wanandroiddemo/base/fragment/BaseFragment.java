package com.example.chen.wanandroiddemo.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 16:19
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements BaseView {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
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

    }

    @Override
    public void showNetChangeTips() {

    }
}
