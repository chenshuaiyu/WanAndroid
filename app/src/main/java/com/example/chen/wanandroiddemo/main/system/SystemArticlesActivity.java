package com.example.chen.wanandroiddemo.main.system;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticlesContract;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemArticlesPresenter;
import com.example.statelayout_lib.StateLayoutManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class SystemArticlesActivity extends BaseLoadActivity<SystemArticlesPresenter> implements SystemArticlesContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private Tab mTab;
    private List<Fragment> mFragmentList = new ArrayList<>();

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

    public static Intent newIntent(Context context, Tab tab) {
        Intent intent = new Intent(context, SystemArticlesActivity.class);
        intent.putExtra(Constants.SYSTEM, tab);
        return intent;
    }

    @Override
    protected void initView() {
        mTab = (Tab) getIntent().getSerializableExtra(Constants.SYSTEM);

        initToolbar();

        for (Tab childrenTab : mTab.getChildren()) {
            mFragmentList.add(SystemArticleFragment.newInstance(childrenTab));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
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
