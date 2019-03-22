package com.example.chen.wanandroiddemo.ui.fragment;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.NavigationContract;
import com.example.chen.wanandroiddemo.di.component.DaggerNavigationComponent;
import com.example.chen.wanandroiddemo.di.module.NavigationModule;
import com.example.chen.wanandroiddemo.presenter.NavigationPresenter;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 15:19
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nagivation;
    }

    @Override
    protected void inject() {
        DaggerNavigationComponent.builder().navigationModule(new NavigationModule()).build().inject(this);
    }

    @Override
    protected void initData() {

    }
}
