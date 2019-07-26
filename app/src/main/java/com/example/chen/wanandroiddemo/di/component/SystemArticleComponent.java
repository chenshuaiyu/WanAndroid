package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemArticleModule;
import com.example.chen.wanandroiddemo.ui.system.SystemArticleFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemArticleModule.class)
public interface SystemArticleComponent {
    void inject(SystemArticleFragment systemArticleFragment);

}
