package com.example.chen.wanandroiddemo.ui.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SearchArticleAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchArticlesComponent;
import com.example.chen.wanandroiddemo.di.module.SearchArticlesModule;
import com.example.chen.wanandroiddemo.presenter.SearchArticlesPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchArticlesActivity extends BaseActivity<SearchArticlesPresenter> implements SearchArticlesContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int curPage = 0;
    private String key;
    private List<Article> mArticles;
    private SearchArticleAdapter mSearchArticleAdapter;

    public static Intent newIntent(Context context, String key){
        Intent intent = new Intent(context, SearchArticlesActivity.class);
        intent.putExtra(Constants.SEARCH_KEY, key);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_articles;
    }

    @Override
    protected void inject() {
        DaggerSearchArticlesComponent.builder().searchArticlesModule(new SearchArticlesModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        key = intent.getStringExtra(Constants.SEARCH_KEY);

        mToolbar.setTitle(key);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mArticles = new ArrayList<>();
        mSearchArticleAdapter = new SearchArticleAdapter(this, mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchArticleAdapter);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 0;
                presenter.getSearchArticles(curPage++, key);
                refreshLayout.finishRefresh(1500);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getSearchArticles(curPage++, key);
                refreshLayout.finishLoadMore(1500);
            }
        });

        presenter.getSearchArticles(curPage++, key);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showSearchArticles(List<Article> articles) {
        mArticles.addAll(articles);
        mSearchArticleAdapter.notifyDataSetChanged();
    }
}
