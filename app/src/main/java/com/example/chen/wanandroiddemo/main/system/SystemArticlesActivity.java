package com.example.chen.wanandroiddemo.main.system;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemArticlesPresenter;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemArticlesActivity extends BaseLoadActivity<SystemArticlesPresenter> implements SystemArticlesContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private System mSystem;
    private List<Fragment> mFragmentList;
    private ViewPagerAdapter mAdapter;

    @Override
    protected SystemArticlesPresenter getPresenter() {
        return new SystemArticlesPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.activity_system_article)
                .setOnReLoadListener(() -> showContentView())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mSystem = (System) getIntent().getSerializableExtra(Constants.SYSTEM);

        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(mSystem.getName());
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mFragmentList = new ArrayList<>();
        for (System childrenSystem : mSystem.getChildren()) {
            SystemArticleFragment articleFragment = new SystemArticleFragment();
            articleFragment.setChildrenSystem(childrenSystem);
            mFragmentList.add(articleFragment);
        }
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
