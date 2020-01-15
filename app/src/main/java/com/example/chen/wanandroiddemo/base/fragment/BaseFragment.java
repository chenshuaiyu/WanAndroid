package com.example.chen.wanandroiddemo.base.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.statelayout_lib.StateLayout;
import com.example.statelayout_lib.StateLayoutManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 16:19
 * <p>
 * 懒加载Fragment
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements BaseView {

    private boolean isViewCreated = false;
    private boolean isLoaded = false;

    private StateLayout mStateLayout;
    private Unbinder mUnbinder;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_state_layout, container, false);
        initPresenter();
        initStateLayout(view);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        showLoadingView();
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && !isHidden() && isViewCreated) {
            reLoad();
            isLoaded = true;
        }
    }

    protected void initPresenter() {
        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    protected void initStateLayout(View view) {
        mStateLayout = view.findViewById(R.id.state_layout);
        StateLayoutManager layoutManager = getStateLayoutManager();

        //设置默认layout
        layoutManager.setLoadingLayoutResId(layoutManager.getLoadingLayoutResId() == 0 ? R.layout.default_loading : layoutManager.getLoadingLayoutResId());
        layoutManager.setEmptyDataLayoutResId(layoutManager.getEmptyDataLayoutResId() == 0 ? R.layout.default_empty_data : layoutManager.getEmptyDataLayoutResId());
        layoutManager.setNetErrorLayoutResId(layoutManager.getNetErrorLayoutResId() == 0 ? R.layout.default_net_error : layoutManager.getNetErrorLayoutResId());
        layoutManager.setErrorLayoutResId(layoutManager.getErrorLayoutResId() == 0 ? R.layout.default_error : layoutManager.getErrorLayoutResId());
        layoutManager.setNetErrorReLoadViewResId(layoutManager.getNetErrorReLoadViewResId() == 0 ? R.id.tv_load : layoutManager.getNetErrorReLoadViewResId());
        layoutManager.setErrorReLoadViewResId(layoutManager.getErrorReLoadViewResId() == 0 ? R.id.tv_load : layoutManager.getErrorReLoadViewResId());

        mStateLayout.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        isViewCreated = false;
        isLoaded = false;
        super.onDestroyView();
    }

    /**
     * 获取Presenter
     *
     * @return
     */
    protected abstract T getPresenter();

    /**
     * 获取StateLayoutManager
     *
     * @return
     */
    protected abstract StateLayoutManager getStateLayoutManager();

    /**
     * 初始化View
     */
    protected abstract void initView();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !isLoaded && mPresenter != null) {
            if (!NetUtil.isNetworkConnected()) {
                showNetErrorView();
            } else {
                reLoad();
                isLoaded = true;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isLoaded && isViewCreated && mPresenter != null) {
            if (!NetUtil.isNetworkConnected()) {
                showNetErrorView();
            } else {
                reLoad();
                isLoaded = true;
            }
        }
    }

    @Override
    public void showContentView() {
        mStateLayout.showContentLayout();
    }

    @Override
    public void showLoadingView() {
        mStateLayout.showLoadingLayout();
    }

    @Override
    public void showEmptyDataView() {
        mStateLayout.showEmptyDataLayout();
    }

    @Override
    public void showNetErrorView() {
        mStateLayout.showNetErrorLayout();
    }

    @Override
    public void showErrorView() {
        mStateLayout.showErrorLayout();
    }

    @Override
    public void reLoad() {
        if (!NetUtil.isNetworkConnected()) {
            showNetErrorView();
        } else {
            mStateLayout.reLoad();
        }
    }

    @Override
    public void useNightMode(boolean isNightMode) {
    }

    @Override
    public void showNetChangeTips() {
        DataManager dataManager = DataManager.getInstance();
        String state = NetUtil.getNetworkType();
        dataManager.setNetState(state);
        ToastUtil.toast(state);
//        reLoad();
    }
}
