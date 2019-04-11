package com.example.chen.wanandroiddemo.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/26 19:05
 */
public abstract class BaseRefreshFragment<T extends BasePresenter> extends BaseLoadFragment<T> {
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    protected SmartRefreshLayout mSmartRefreshLayout;

    public abstract void refresh(RefreshLayout refreshLayout);//上拉刷新
    public abstract void loadMore(RefreshLayout refreshLayout);//下拉加载

    @Override
    protected int getLayoutId() {
        return R.layout.common_refresh_recycler_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            refresh(refreshLayout);
            refreshLayout.finishRefresh(1500);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            loadMore(refreshLayout);
            refreshLayout.finishLoadMore(1500);
        });
    }
}
