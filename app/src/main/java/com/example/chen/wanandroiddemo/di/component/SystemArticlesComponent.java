package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemArticlesModule;
import com.example.chen.wanandroiddemo.ui.system.SystemArticlesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemArticlesModule.class)
public interface SystemArticlesComponent {
    void inject(SystemArticlesActivity systemArticlesActivity);

}
