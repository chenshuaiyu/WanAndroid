package com.example.chen.wanandroiddemo.main.coin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.coin.contract.CoinContract;
import com.example.chen.wanandroiddemo.main.coin.presenter.CoinPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class CoinActivity extends BaseActivity<CoinPresenter> implements CoinContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragments = Arrays.asList(new CoinRankFragment(), new MyCoinFragment());

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_coin;
    }

    @Override
    protected CoinPresenter getPresenter() {
        return new CoinPresenter(DataManager.getInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.coin_rank);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_welfare:
                OpenActivityUtil.openArticleDetailActivity(this, Constants.COIN_WELFARE, "本站福利");
                break;
            case R.id.menu_coin_rule:
                OpenActivityUtil.openArticleDetailActivity(this, Constants.COIN_RULE_LINK, "本站积分规则");
                break;
            default:
                break;
        }
        return true;
    }
}
