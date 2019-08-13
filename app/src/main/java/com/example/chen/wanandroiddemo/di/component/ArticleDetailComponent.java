package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.ArticleDetailModule;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 20:13
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = ArticleDetailModule.class)
public interface ArticleDetailComponent {
    void inject(ArticleDetailActivity articleDetailActivity);

}
