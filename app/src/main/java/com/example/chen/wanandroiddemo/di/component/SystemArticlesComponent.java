package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.SystemArticlesModule;
import com.example.chen.wanandroiddemo.ui.system.SystemArticlesActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = SystemArticlesModule.class)
public interface SystemArticlesComponent {
    void inject(SystemArticlesActivity systemArticlesActivity);

}
