package com.example.chen.wanandroiddemo.ui.activity;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchComponent;
import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.presenter.SearchPresenter;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject() {
        DaggerSearchComponent.builder().searchModule(new SearchModule()).build().inject(this);
    }

    @Override
    protected void initData() {

    }

}
