package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SearchArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchArticlesComponent;
import com.example.chen.wanandroiddemo.di.module.SearchArticlesModule;
import com.example.chen.wanandroiddemo.presenter.SearchArticlesPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class SearchArticlesActivity extends BaseActivity<SearchArticlesPresenter> implements SearchArticlesContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;



    private String key;

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



    }

    @Override
    public void showSearchArticles(List<Article> articles) {

    }
}
