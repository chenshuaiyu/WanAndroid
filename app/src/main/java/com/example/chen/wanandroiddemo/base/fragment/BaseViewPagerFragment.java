package com.example.chen.wanandroiddemo.base.fragment;

import android.support.v4.view.ViewPager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 19:46
 */
public abstract class BaseViewPagerFragment<T extends BasePresenter> extends BaseFragment<T> {

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected SlidingTabLayout mTabLayout;

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.common_tab_view_pager)
                .build();
    }
}
