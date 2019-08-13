package com.example.chen.wanandroiddemo.ui.system;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.contract.SystemArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.di.component.DaggerSystemArticlesComponent;
import com.example.chen.wanandroiddemo.di.module.SystemArticlesModule;
import com.example.chen.wanandroiddemo.presenter.system.SystemArticlesPresenter;
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
    protected int getLayoutId() {
        return R.layout.activity_system_article;
    }

    @Override
    protected void inject() {
        DaggerSystemArticlesComponent.builder()
                .appComponent(((WanAndroidApp)getApplication()).getAppComponent())
                .systemArticlesModule(new SystemArticlesModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mSystem = (System) getIntent().getSerializableExtra(Constants.SYSTEM);

        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(mSystem.getName());
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mFragmentList = new ArrayList<>();
        for (System childrenSystem : mSystem.getChildren()) {
            mFragmentList.add(new SystemArticleFragment(childrenSystem));
        }
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        showNormalView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFragmentList.clear();
        for (System childrenSystem : mSystem.getChildren()) {
            mFragmentList.add(new SystemArticleFragment(childrenSystem));
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
