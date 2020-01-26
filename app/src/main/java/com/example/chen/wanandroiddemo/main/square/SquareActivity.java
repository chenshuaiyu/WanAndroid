package com.example.chen.wanandroiddemo.main.square;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.SquareContract;
import com.example.chen.wanandroiddemo.main.square.presenter.SquarePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class SquareActivity extends BaseActivity<SquarePresenter> implements SquareContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragments = Arrays.asList(new SquareListFragment(), new MySquareFragment());

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_square;
    }

    @Override
    protected SquarePresenter getPresenter() {
        return new SquarePresenter(DataManager.getInstance());
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
        mToolbar.setTitle(R.string.square);
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
