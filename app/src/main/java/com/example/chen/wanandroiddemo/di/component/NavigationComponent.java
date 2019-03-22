package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.NavigationModule;
import com.example.chen.wanandroiddemo.di.module.SystemModule;
import com.example.chen.wanandroiddemo.ui.fragment.NavigationFragment;
import com.example.chen.wanandroiddemo.ui.fragment.SystemFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = NavigationModule.class)
public interface NavigationComponent {
    void inject(NavigationFragment navigationFragment);

}
