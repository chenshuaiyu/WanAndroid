package com.example.chen.wanandroiddemo.main.system;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemArticlePresenter;
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

    private static final String BUNDLE_SYSTEM_ARTICLE = "system_article";

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private Tab mChildrenTab;
    private List<Article> mArticles = new ArrayList<>();
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

    public static SystemArticleFragment newInstance(Tab tab) {
        Bundle args = new Bundle();
        args.putSerializable(SystemArticleFragment.BUNDLE_SYSTEM_ARTICLE, tab);
        SystemArticleFragment fragment = new SystemArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        assert getArguments() != null;
        mChildrenTab = (Tab) getArguments().getSerializable(BUNDLE_SYSTEM_ARTICLE);

        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getSystemArticles(firstPage, mChildrenTab.getId());
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getSystemArticles(page, mChildrenTab.getId());
            }
        });
    }

    @Override
    public void showSystemArticles(List<Article> articles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mArticles.clear();
        }
        mArticles.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        assert getArguments() != null;
        mChildrenTab = (Tab) getArguments().getSerializable(BUNDLE_SYSTEM_ARTICLE);
        assert mChildrenTab != null;
        return mChildrenTab.getName();
    }
}
