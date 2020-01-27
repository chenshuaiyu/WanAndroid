package com.example.chen.wanandroiddemo.main.collection;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionPresenter;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class CollectionActivity extends BaseActivity<CollectionPresenter> implements CollectionContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragments = Arrays.asList(new CollectionArticleFragment(), new CollectionWebsiteFragment());

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_collection;
    }

    @Override
    protected CollectionPresenter getPresenter() {
        return new CollectionPresenter(DataManager.getInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();
        initToolbar();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.my_collection);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_collect_outside_article:
                //收藏站外文章
                View collectOutsideArticleView = LayoutInflater.from(this).inflate(R.layout.dialog_collect_outside_article, null);
                new AlertDialog.Builder(CollectionActivity.this)
                        .setTitle(R.string.collect_outside_article)
                        .setView(collectOutsideArticleView)
                        .setCancelable(false)
                        .setPositiveButton(R.string.collect, (dialog1, which) -> {
                            EditText titleEt = collectOutsideArticleView.findViewById(R.id.et_title);
                            EditText authorEt = collectOutsideArticleView.findViewById(R.id.et_author);
                            EditText linkEt = collectOutsideArticleView.findViewById(R.id.et_link);
                            mPresenter.collectOutsideArticle(titleEt.getText().toString(), authorEt.getText().toString(), linkEt.getText().toString());
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })
                        .show();
                break;
            case R.id.menu_collect_website:
                //收藏网址
                View collectWebsiteView = LayoutInflater.from(this).inflate(R.layout.dialog_collect_website, null);
                new AlertDialog.Builder(CollectionActivity.this)
                        .setTitle(R.string.collect_website)
                        .setView(collectWebsiteView)
                        .setCancelable(false)
                        .setPositiveButton(R.string.collect, (dialog1, which) -> {
                            EditText nameEt = collectWebsiteView.findViewById(R.id.et_name);
                            EditText linkEt = collectWebsiteView.findViewById(R.id.et_link);
                            mPresenter.collectWebsite(nameEt.getText().toString(), linkEt.getText().toString());
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showCollectResult(boolean success) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }
}
