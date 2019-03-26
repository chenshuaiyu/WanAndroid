package com.example.chen.wanandroiddemo.base.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/26 19:46
 */
public abstract class BaseViewPagerFragment<T extends BasePresenter> extends BaseFragment<T>  {
    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.common_tab_view_pager;
    }

}
