package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.HomeModule;
import com.example.chen.wanandroiddemo.ui.fragment.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);

}
