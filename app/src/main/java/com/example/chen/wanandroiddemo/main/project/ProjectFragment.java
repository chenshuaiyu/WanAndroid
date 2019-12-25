package com.example.chen.wanandroiddemo.main.project;

import android.support.v4.app.Fragment;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerProjectComponent;
import com.example.chen.wanandroiddemo.di.module.ProjectModule;
import com.example.chen.wanandroiddemo.main.project.presenter.ProjectPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:34
 */
public class ProjectFragment extends BaseViewPagerFragment<ProjectPresenter> implements ProjectContract.View {
    private List<Fragment> mFragments;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void inject() {
        DaggerProjectComponent.builder()
                .appComponent(((WanAndroidApp)getActivity().getApplication()).getAppComponent())
                .projectModule(new ProjectModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mFragments = new ArrayList<>();
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void reLoad() {
        presenter.getProjectTab();
    }

    @Override
    public void showTab(List<Tab> projectTabList) {
        mFragments.clear();
        for (Tab tab : projectTabList) {
            mFragments.add(new ProjectTabFragment(tab));
        }
        mPagerAdapter.notifyDataSetChanged();
        mTabLayout.notifyDataSetChanged();
    }
}
