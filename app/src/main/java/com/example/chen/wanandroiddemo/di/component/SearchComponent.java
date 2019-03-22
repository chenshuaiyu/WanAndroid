package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.ui.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 22:19
 */
@Singleton
@Component(modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivity);

}
