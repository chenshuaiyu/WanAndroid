package com.example.chen.wanandroiddemo.main.articledetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.articledetail.contract.ArticleContract;
import com.example.chen.wanandroiddemo.main.articledetail.presenter.ArticlePresenter;
import com.example.chen.wanandroiddemo.utils.ToastUtil;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 */
public class ArticleActivity extends BaseActivity<ArticlePresenter> implements ArticleContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String url;

    public static Intent newIntent(Context context, String link, String title) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(Constants.ARTICLE_URL, link);
        intent.putExtra(Constants.ARTICLE_TITLE, title);
        return intent;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_article;
    }

    @Override
    protected ArticlePresenter getPresenter() {
        return new ArticlePresenter(DataManager.getInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();

        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.ARTICLE_URL);
        String title = intent.getStringExtra(Constants.ARTICLE_TITLE);

        initToolbar(title);

        ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(url);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, fragment)
                .commit();
    }

    private void initToolbar(String title) {
        mToolbar.setTitle(Html.fromHtml(title));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
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
            case R.id.menu_open_with_browser:
                ToastUtil.toast(R.string.opening_in_browser);
                openWithBrowser();
                break;
            default:
                break;
        }
        return true;
    }

    private void openWithBrowser() {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
