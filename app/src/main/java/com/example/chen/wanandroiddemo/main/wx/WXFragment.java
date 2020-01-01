package com.example.chen.wanandroiddemo.main.wx;

import android.support.v4.app.Fragment;

import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.wx.contract.WXContract;
import com.example.chen.wanandroiddemo.main.wx.presenter.WXPresenter;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 16:40
 */
public class WXFragment extends BaseViewPagerFragment<WXPresenter> implements WXContract.View {
    private List<Fragment> mFragments;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected WXPresenter getPresenter() {
        return new WXPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        StateLayoutManager manager = super.getStateLayoutManager();
        manager.setOnReLoadListener(() -> mPresenter.getWXTab());
        return manager;
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mFragments = new ArrayList<>();
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void showTab(List<Tab> wxTabList) {
        mFragments.clear();
        for (Tab tab : wxTabList) {
            WxTabFragment tabFragment = new WxTabFragment();
            tabFragment.setWXTab(tab);
            mFragments.add(tabFragment);
        }
        mPagerAdapter.notifyDataSetChanged();
        mTabLayout.notifyDataSetChanged();
    }
}
