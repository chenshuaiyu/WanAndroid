package com.example.chen.wanandroiddemo.ui.system;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SystemArticleAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.di.component.DaggerSystemArticleComponent;
import com.example.chen.wanandroiddemo.di.module.SystemArticleModule;
import com.example.chen.wanandroiddemo.presenter.SystemArticlePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 20:12
 */
@SuppressLint("ValidFragment")
public class SystemArticleFragment extends BaseRefreshFragment<SystemArticlePresenter> implements SystemArticleContract.View {
    private int curPage = 0;
    private System mChildrenSystem;
    private List<Article> mArticles;
    private SystemArticleAdapter mAdapter;

    public SystemArticleFragment(System childrenSystem) {
        mChildrenSystem = childrenSystem;
    }

    public System getChildrenSystem() {
        return mChildrenSystem;
    }

    @Override
    protected void inject() {
        DaggerSystemArticleComponent.builder().systemArticleModule(new SystemArticleModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mArticles = new ArrayList<>();
        mAdapter = new SystemArticleAdapter(getActivity(), mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        presenter.getSystemArticles(curPage++, mChildrenSystem.getId());
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 0;
        presenter.getSystemArticles(curPage, mChildrenSystem.getId());
        refreshLayout.finishRefresh(1500);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getSystemArticles(curPage++, mChildrenSystem.getId());
        refreshLayout.finishLoadMore(1500);
    }

    @Override
    public void showSystemArticles(List<Article> articles) {
        if (curPage == 0)
            mArticles.clear();
        mArticles.addAll(articles);
        mAdapter.notifyDataSetChanged();
    }
}
