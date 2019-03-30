package com.example.chen.wanandroiddemo.ui.system;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SystemArticlesContract;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.di.component.DaggerSystemArticlesComponent;
import com.example.chen.wanandroiddemo.di.module.SystemArticlesModule;
import com.example.chen.wanandroiddemo.presenter.SystemArticlesPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemArticlesActivity extends BaseActivity<SystemArticlesPresenter> implements SystemArticlesContract.View {
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
        DaggerSystemArticlesComponent.builder().systemArticlesModule(new SystemArticlesModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        mFragmentList = new ArrayList<>();
        mSystem = (System) getIntent().getSerializableExtra(Constants.SYSTEM);
        for (System childrenSystem : mSystem.getChildren()) {
            mFragmentList.add(new SystemArticleFragment(childrenSystem));
        }
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
}
