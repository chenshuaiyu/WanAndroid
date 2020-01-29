package com.example.chen.wanandroiddemo.main.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.main.search.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.main.search.presenter.SearchArticlesPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class SearchArticlesActivity extends BaseActivity<SearchArticlesPresenter> implements SearchArticlesContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private String key;
    private List<Article> mArticles = new ArrayList<>();
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
        key = getIntent().getStringExtra(Constants.SEARCH_KEY);
        initToolbar();

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(this, article.getLink(), article.getTitle());
        });
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            switch (view.getId()) {
                case R.id.ll_chapter:
                    OpenActivityUtil.openSystemArticlesActivity(this,
                            article.getSuperChapterName(), article.getChapterName(), article.getChapterId());
                    break;
                case R.id.iv_collect:
                    if (!article.isCollect()) {
                        mPresenter.collectArticle(article.getId(), position);
                    } else {
                        mPresenter.cancelCollectArticle(article.getId(), position);
                    }
                    break;
                default:
                    break;
            }
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getSearchArticles(firstPage, key);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getSearchArticles(page, key);
            }
        });

        //初始化第一页数据
        mRefreshRecyclerView.reLoad();
    }

    private void initToolbar() {
        mToolbar.setTitle(key);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public void showSearchArticles(List<Article> articles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mArticles.clear();
        }
        mArticles.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mArticles.get(position).setCollect(true);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mArticles.get(position).setCollect(false);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
