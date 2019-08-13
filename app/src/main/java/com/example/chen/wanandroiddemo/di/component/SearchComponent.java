package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.ui.search.SearchActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 22:19
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivity);

}
