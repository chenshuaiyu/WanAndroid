package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.chen.wanandroiddemo.ui.fragment.WxTabFragment;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 18:04
 */
public class WXTabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;

    public WXTabViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        WxTabFragment fragment = (WxTabFragment) mFragments.get(position);
        return fragment.getWXTab().getName();
    }
}
