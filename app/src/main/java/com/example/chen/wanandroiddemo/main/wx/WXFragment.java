package com.example.chen.wanandroiddemo.main.wx;

import androidx.fragment.app.Fragment;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.wx.contract.WXContract;
import com.example.chen.wanandroiddemo.main.wx.presenter.WXPresenter;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 16:40
 */
public class WXFragment extends BaseViewPagerFragment<WXPresenter> implements WXContract.View {

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected WXPresenter getPresenter() {
        return new WXPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.common_tab_view_pager)
                .setOnReLoadListener(() -> mPresenter.getWXTab())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();
    }

    @Override
    public void showTab(List<Tab> wxTabList) {
        mFragments.clear();
        for (Tab tab : wxTabList) {
            mFragments.add(WXTabFragment.newInstance(tab));
        }
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
