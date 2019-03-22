package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemArticlesModule;
import com.example.chen.wanandroiddemo.ui.activity.SystemArticlesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemArticlesModule.class)
public interface SystemArticlesComponent {
    void inject(SystemArticlesActivity systemArticlesActivity);

}
