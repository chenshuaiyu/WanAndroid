package com.example.chen.wanandroiddemo.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayout;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

public abstract class BaseLoadActivity<T extends IPresenter> extends BaseActivity<T> {

    private StateLayout mStateLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.default_state_layout;
    }

    @Override
    protected void init() {
        super.init();
        initStateLayout();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //控制页面状态
        if (NetUtil.isNetworkConnected()) {
            showLoadingView();
        } else {
            showNetErrorView();
        }

        initView();
        reLoad();
    }

    private void initStateLayout() {
        mStateLayout = findViewById(R.id.state_layout);
        StateLayoutManager layoutManager = getStateLayoutManager();

        //设置默认layout
        layoutManager.setLoadingLayoutResId(layoutManager.getLoadingLayoutResId() == 0 ? R.layout.default_loading : layoutManager.getLoadingLayoutResId());
        layoutManager.setEmptyDataLayoutResId(layoutManager.getEmptyDataLayoutResId() == 0 ? R.layout.default_empty_data : layoutManager.getEmptyDataLayoutResId());
        layoutManager.setNetErrorLayoutResId(layoutManager.getNetErrorLayoutResId() == 0 ? R.layout.default_net_error : layoutManager.getNetErrorLayoutResId());
        layoutManager.setErrorLayoutResId(layoutManager.getErrorLayoutResId() == 0 ? R.layout.default_error : layoutManager.getErrorLayoutResId());
        layoutManager.setNetErrorReLoadViewResId(R.id.tv_load);
        layoutManager.setErrorReLoadViewResId(R.id.tv_load);

        mStateLayout.setLayoutManager(layoutManager);
    }

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
        mStateLayout.reLoad();
    }
}
