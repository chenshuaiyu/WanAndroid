package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.ArticleDetailModule;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 20:13
 */
@Singleton
@Component(modules = ArticleDetailModule.class)
public interface ArticleDetailComponent {
    void inject(ArticleDetailActivity articleDetailActivity);

}
