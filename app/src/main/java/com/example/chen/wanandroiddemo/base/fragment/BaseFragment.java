package com.example.chen.wanandroiddemo.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 16:19
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements BaseView {

    @Inject
    protected T presenter;

    @LayoutRes
    protected abstract int getLayoutId();
    protected abstract void inject();
    protected abstract void initData();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        presenter.attachView(this);
    }
}
