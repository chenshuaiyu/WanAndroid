package com.example.chen.wanandroiddemo.main.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.search.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.main.search.presenter.SearchArticlesPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
    private List<Article> mArticles = new ArrayList<>();;
    private ArticlesAdapter mArticlesAdapter;

    public static Intent newIntent(Context context, String key) {
        Intent intent = new Intent(context, SearchArticlesActivity.class);
        intent.putExtra(Constants.SEARCH_KEY, key);
        return intent;
    }

    @Override
    protected SearchArticlesPresenter getPresenter() {
        return new SearchArticlesPresenter(DataManager.getInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_articles;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();

        Intent intent = getIntent();
        key = intent.getStringExtra(Constants.SEARCH_KEY);

        initToolbar();

        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(this, article.getId(), article.getLink(), article.getTitle());
        });
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);

            switch (view.getId()) {
                case R.id.ll_chapter:
                    OpenActivityUtil.openSystemArticlesActivity(this,
                            article.getSuperChapterName(), article.getChapterName(), article.getChapterId());
                    break;
                case R.id.iv_collect:
                    break;
                default:
                    break;
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            curPage = 0;
            mPresenter.getSearchArticles(curPage++, key);
            refreshLayout.finishRefresh(1500);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.getSearchArticles(curPage++, key);
            refreshLayout.finishLoadMore(1500);
        });

        mPresenter.getSearchArticles(curPage++, key);
    }

    private void initToolbar() {
        mToolbar.setTitle(key);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
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
        mArticlesAdapter.notifyDataSetChanged();
    }
}
