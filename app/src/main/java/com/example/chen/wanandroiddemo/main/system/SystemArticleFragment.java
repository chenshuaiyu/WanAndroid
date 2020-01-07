package com.example.chen.wanandroiddemo.main.system;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemArticlePresenter;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 20:12
 */
public class SystemArticleFragment extends BaseFragment<SystemArticlePresenter> implements SystemArticleContract.View {

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private System mChildrenSystem;
    private List<Article> mArticles;
    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected SystemArticlePresenter getPresenter() {
        return new SystemArticlePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_refresh_recycler_view)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mArticles = new ArrayList<>();
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), article.getId(), article.getLink(), article.getTitle());
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getSystemArticles(firstPage, mChildrenSystem.getId());
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getSystemArticles(page, mChildrenSystem.getId());
            }
        });
    }

    @Override
    public void showSystemArticles(List<Article> articles) {
        if (mRefreshRecyclerView.isFirstPage())
            mArticles.clear();
        mArticles.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    public void setChildrenSystem(System childrenSystem) {
        mChildrenSystem = childrenSystem;
    }

    @Override
    public String toString() {
        return mChildrenSystem.getName();
    }
}
