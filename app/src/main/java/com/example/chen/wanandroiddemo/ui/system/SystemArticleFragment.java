package com.example.chen.wanandroiddemo.ui.system;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.di.component.DaggerSystemArticleComponent;
import com.example.chen.wanandroiddemo.di.module.SystemArticleModule;
import com.example.chen.wanandroiddemo.presenter.system.SystemArticlePresenter;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.example.chen.wanandroiddemo.utils.JumpUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 20:12
 */
@SuppressLint("ValidFragment")
public class SystemArticleFragment extends BaseRefreshFragment<SystemArticlePresenter> implements SystemArticleContract.View {
    private int curPage = 0;
    private System mChildrenSystem;
    private List<Article> mArticles;
    private ArticlesAdapter mArticlesAdapter;

    public SystemArticleFragment(System childrenSystem) {
        mChildrenSystem = childrenSystem;
    }

    @Override
    protected void inject() {
        DaggerSystemArticleComponent.builder().systemArticleModule(new SystemArticleModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mArticles = new ArrayList<>();
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            JumpUtils.jumpToArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });
    }

    @Override
    public void reLoad() {
        curPage = 0;
        presenter.getSystemArticles(curPage++, mChildrenSystem.getId());
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 0;
        presenter.getSystemArticles(curPage, mChildrenSystem.getId());
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getSystemArticles(curPage++, mChildrenSystem.getId());
    }

    @Override
    public void showSystemArticles(List<Article> articles) {
        if (curPage == 0)
            mArticles.clear();
        mArticles.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public String toString() {
        return mChildrenSystem.getName();
    }
}
