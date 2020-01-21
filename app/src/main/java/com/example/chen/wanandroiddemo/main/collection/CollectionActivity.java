package com.example.chen.wanandroiddemo.main.collection;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionPresenter;
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
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
