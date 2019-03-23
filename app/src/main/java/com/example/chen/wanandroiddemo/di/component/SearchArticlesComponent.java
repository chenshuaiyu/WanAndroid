package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SearchArticlesModule;
import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.ui.activity.SearchActivity;
import com.example.chen.wanandroiddemo.ui.activity.SearchArticlesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 22:19
 */
@Singleton
@Component(modules = SearchArticlesModule.class)
public interface SearchArticlesComponent {
    void inject(SearchArticlesActivity searchArticlesActivity);

}
