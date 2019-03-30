package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.chen.wanandroiddemo.ui.project.ProjectTabFragment;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 8:44
 */
public class ProjectTabViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public ProjectTabViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
        ProjectTabFragment fragment = (ProjectTabFragment) mFragments.get(position);
        return fragment.getProjectTab().getName();
    }
}
