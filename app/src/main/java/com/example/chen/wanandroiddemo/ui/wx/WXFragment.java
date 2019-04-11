package com.example.chen.wanandroiddemo.ui.wx;

import android.support.v4.app.Fragment;

import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.contract.WXContract;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerWXComponent;
import com.example.chen.wanandroiddemo.di.module.WXModule;
import com.example.chen.wanandroiddemo.presenter.wx.WXPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 16:40
 */
public class WXFragment extends BaseViewPagerFragment<WXPresenter> implements WXContract.View {
    private List<Fragment> mFragments;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void inject() {
        DaggerWXComponent.builder().wXModule(new WXModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void reLoad() {
        presenter.getWXTab();
    }

    @Override
    public void showTab(List<Tab> wxTabList) {
        mFragments.clear();
        for (Tab tab : wxTabList)
            mFragments.add(new WxTabFragment(tab));
        mPagerAdapter.notifyDataSetChanged();
    }
}
