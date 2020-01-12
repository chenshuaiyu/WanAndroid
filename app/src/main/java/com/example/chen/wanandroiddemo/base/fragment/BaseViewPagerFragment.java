package com.example.chen.wanandroiddemo.base.fragment;

import androidx.viewpager.widget.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.statelayout_lib.StateLayoutManager;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 19:46
 */
public abstract class BaseViewPagerFragment<T extends BasePresenter> extends BaseFragment<T> {

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected TabLayout mTabLayout;

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.common_tab_view_pager)
                .build();
    }
}
