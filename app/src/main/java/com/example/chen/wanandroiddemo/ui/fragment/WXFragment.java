package com.example.chen.wanandroiddemo.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.WXTabViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.HomeContract;
import com.example.chen.wanandroiddemo.contract.WXContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.WXTab;
import com.example.chen.wanandroiddemo.di.component.DaggerWXComponent;
import com.example.chen.wanandroiddemo.di.module.WXModule;
import com.example.chen.wanandroiddemo.presenter.WXPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 16:40
 */
public class WXFragment extends BaseFragment<WXPresenter> implements WXContract.View {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private List<Fragment> mFragments;
    private WXTabViewPagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx;
    }

    @Override
    protected void inject() {
        DaggerWXComponent.builder().wXModule(new WXModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mPagerAdapter = new WXTabViewPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        presenter.getWXTab();
    }

    @Override
    public void showTab(List<WXTab> wxTabList) {
        for (WXTab tab : wxTabList) {
            mFragments.add(new WxTabFragment(tab));
        }
        mPagerAdapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
