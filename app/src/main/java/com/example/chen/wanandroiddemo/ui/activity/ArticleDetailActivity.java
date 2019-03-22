package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.ArticleDetailContract;
import com.example.chen.wanandroiddemo.di.component.DaggerArticleDetailComponent;
import com.example.chen.wanandroiddemo.di.module.ArticleDetailModule;
import com.example.chen.wanandroiddemo.presenter.ArticleDetailPresenter;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;

public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame_container)
    FrameLayout mFrameLayout;

    private AgentWeb mAgentWeb;
    private String url;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void inject() {
        DaggerArticleDetailComponent.builder().articleDetailModule(new ArticleDetailModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.ARTICLE_URL);
        setSupportActionBar(mToolbar);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary))
                .setMainFrameErrorView(R.layout.error_view, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);
        return true;
    }
}