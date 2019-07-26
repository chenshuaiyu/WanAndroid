package com.example.chen.wanandroiddemo.base.fragment;

import android.support.v4.view.ViewPager;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.flyco.tablayout.SlidingTabLayout;
import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 19:46
 */
public abstract class BaseViewPagerFragment<T extends BasePresenter> extends BaseLoadFragment<T>  {
    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected SlidingTabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.common_tab_view_pager;
    }
}
