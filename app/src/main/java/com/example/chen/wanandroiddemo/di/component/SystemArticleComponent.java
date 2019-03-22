package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemArticleModule;
import com.example.chen.wanandroiddemo.ui.fragment.SystemArticleFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemArticleModule.class)
public interface SystemArticleComponent {
    void inject(SystemArticleFragment systemArticleFragment);

}
