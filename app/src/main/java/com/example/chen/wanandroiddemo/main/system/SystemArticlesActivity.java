package com.example.chen.wanandroiddemo.main.system;

import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.statelayout_lib.StateLayoutManager;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticlesContract;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemArticlesPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class SystemArticlesActivity extends BaseLoadActivity<SystemArticlesPresenter> implements SystemArticlesContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Tab mTab;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPagerAdapter mAdapter;

    @Override
    protected SystemArticlesPresenter getPresenter() {
        return new SystemArticlesPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.activity_system_article)
                .setOnReLoadListener(this::showContentView)
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();
        mTab = (Tab) getIntent().getSerializableExtra(Constants.SYSTEM);

        initToolbar();

        for (Tab childrenTab : mTab.getChildren()) {
            SystemArticleFragment articleFragment = new SystemArticleFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(SystemArticleFragment.BUNDLE_SYSTEM_ARTICLE, childrenTab);
            articleFragment.setArguments(bundle);
            mFragmentList.add(articleFragment);
        }
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        mToolbar.setTitle(mTab.getName());
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
