package com.example.chen.wanandroiddemo.ui.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchArticlesComponent;
import com.example.chen.wanandroiddemo.di.module.SearchArticlesModule;
import com.example.chen.wanandroiddemo.presenter.search.SearchArticlesPresenter;
import com.example.chen.wanandroiddemo.utils.JumpUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchArticlesActivity extends BaseLoadActivity<SearchArticlesPresenter> implements SearchArticlesContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int curPage = 0;
    private String key;
    private List<Article> mArticles;
    private ArticlesAdapter mArticlesAdapter;

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
        presenter.subscribeEvent();

        Intent intent = getIntent();
        key = intent.getStringExtra(Constants.SEARCH_KEY);

        mToolbar.setTitle(key);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mArticles = new ArrayList<>();
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            JumpUtils.jumpToArticleDetailActivity(this, article.getLink(), article.getTitle());
        });
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);

            switch (view.getId()) {
                case R.id.chapter:
                    JumpUtils.jumpToSystemArticlesActivity(this,
                            article.getSuperChapterName(), article.getChapterName(), article.getChapterId());
                    break;
                case R.id.collect:
                    break;
                default:
                    break;
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            curPage = 0;
            presenter.getSearchArticles(curPage++, key);
            refreshLayout.finishRefresh(1500);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.getSearchArticles(curPage++, key);
            refreshLayout.finishLoadMore(1500);
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
        mArticlesAdapter.notifyDataSetChanged();
    }
}
