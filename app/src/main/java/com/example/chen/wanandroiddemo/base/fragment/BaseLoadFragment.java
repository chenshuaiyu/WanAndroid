package com.example.chen.wanandroiddemo.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.utils.NetUtils;
import com.ldoublem.loadingviewlib.view.LVEatBeans;

import static com.example.chen.wanandroiddemo.app.Constants.*;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/9 21:35
 */
public abstract class BaseLoadFragment<T extends IPresenter> extends BaseFragment<T> {
    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;
    private int mCurrentState = NORMAL_VIEW_STATE;

    @Override
    protected void initView() {
        if (getView() == null) return;
        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null)
            throw new IllegalStateException("mNormalView is null.");
        if (!(mNormalView.getParent() instanceof ViewGroup))
            throw new IllegalStateException("mNormalView is not instanceof ViewGroup.");

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(getActivity(), R.layout.error_view, parent);
        View.inflate(getActivity(), R.layout.loading_view, parent);
        mErrorView = parent.findViewById(R.id.error);
        mLoadingView = parent.findViewById(R.id.loading);

        //设置重加载
        ImageView reloadView = mErrorView.findViewById(R.id.reload_view);
        reloadView.setOnClickListener(v -> reLoad());

        //设置加载动画
        LVEatBeans lvEatBeans =  mLoadingView.findViewById(R.id.loading_view);
        lvEatBeans.setViewColor(R.color.colorPrimary);
        lvEatBeans.setEyeColor(R.color.colorAccent);
        lvEatBeans.startAnim();

        mNormalView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);

        if (NetUtils.isNetworkConnected())
            showLoadingView();
        else
            showErrorView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reLoad();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && presenter != null) {
            reLoad();
            if (!NetUtils.isNetworkConnected())
                showErrorView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && presenter != null) {
            reLoad();
            if (!NetUtils.isNetworkConnected())
                showErrorView();
        }
    }

    @Override
    public void showErrorView() {
        if (mCurrentState != ERROR_VIEW_STATE) {
            hideCurrentView();
            mCurrentState = ERROR_VIEW_STATE;
            showCurrentView();
        }
    }

    @Override
    public void showLoadingView() {
        if (mCurrentState != LOADING_VIEW_STATE) {
            hideCurrentView();
            mCurrentState = LOADING_VIEW_STATE;
            showCurrentView();
        }
    }

    @Override
    public void showNormalView() {
        if (mCurrentState != NORMAL_VIEW_STATE) {
            hideCurrentView();
            mCurrentState = NORMAL_VIEW_STATE;
            showCurrentView();
        }
    }

    private void showCurrentView() {
        View showView = null;
        switch (mCurrentState) {
            case NORMAL_VIEW_STATE:
                showView = mNormalView;
                break;
            case LOADING_VIEW_STATE:
                showView = mLoadingView;
                break;
            case ERROR_VIEW_STATE:
                showView = mErrorView;
                break;
            default:
                break;
        }
        if (showView == null) return;
        showView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        View hideView = null;
        switch (mCurrentState) {
            case NORMAL_VIEW_STATE:
                hideView = mNormalView;
                break;
            case LOADING_VIEW_STATE:
                hideView = mLoadingView;
                break;
            case ERROR_VIEW_STATE:
                hideView = mErrorView;
                break;
            default:
                break;
        }
        if (hideView == null) return;
        hideView.setVisibility(View.GONE);
    }
}
