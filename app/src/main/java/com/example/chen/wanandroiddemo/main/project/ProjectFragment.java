package com.example.chen.wanandroiddemo.main.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.project.presenter.ProjectPresenter;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:34
 */
public class ProjectFragment extends BaseViewPagerFragment<ProjectPresenter> implements ProjectContract.View {

    private List<Fragment> mFragments = new ArrayList<>();
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected ProjectPresenter getPresenter() {
        return new ProjectPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.common_tab_view_pager)
                .setOnReLoadListener(() -> mPresenter.getProjectTab())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();
    }

    @Override
    public void showTab(List<Tab> projectTabList) {
        mFragments.clear();
        for (Tab tab : projectTabList) {
            ProjectTabFragment tabFragment = new ProjectTabFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ProjectTabFragment.BUNDLE_PROJECT_TAB, tab);
            tabFragment.setArguments(bundle);
            mFragments.add(tabFragment);
        }
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
