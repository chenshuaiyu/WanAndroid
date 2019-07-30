package com.example.chen.wanandroiddemo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.ArticleDetailContract;
import com.example.chen.wanandroiddemo.di.component.DaggerArticleDetailComponent;
import com.example.chen.wanandroiddemo.di.module.ArticleDetailModule;
import com.example.chen.wanandroiddemo.presenter.activity.ArticleDetailPresenter;
import com.example.chen.wanandroiddemo.utils.NetUtil;
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
    private String title;

    public static Intent newIntent(Context context, String link, String title) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_URL, link);
        intent.putExtra(Constants.ARTICLE_TITLE, title);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void inject() {
        DaggerArticleDetailComponent.builder().articleDetailModule(new ArticleDetailModule()).build().inject(this);
    }

    @Override
    protected void initView() {
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initData() {
        presenter.subscribeEvent();

        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.ARTICLE_URL);
        title = intent.getStringExtra(Constants.ARTICLE_TITLE);

        mToolbar.setTitle(Html.fromHtml(title));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary))
                .setMainFrameErrorView(R.layout.error_view, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(url);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setBlockNetworkImage(presenter.getNoImageMode());
        if (presenter.getAutoCache()) {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDatabaseEnabled(true);
            if (NetUtil.isNetworkConnected()) {
                //默认从网络获取
                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                //若有缓存，使用缓存，否则从网络获取
                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            mSettings.setAppCacheEnabled(false);
            mSettings.setDatabaseEnabled(false);
            //不使用缓存，只从网络获取
            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        //支持缩放
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_collect:
                break;
            case R.id.menu_open_with_browser:
                Intent intent= new Intent();
                intent.setData(Uri.parse(url));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}
