package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.NavigationModule;
import com.example.chen.wanandroiddemo.ui.navigation.NavigationFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Singleton
@Component(modules = NavigationModule.class)
public interface NavigationComponent {
    void inject(NavigationFragment navigationFragment);

}
