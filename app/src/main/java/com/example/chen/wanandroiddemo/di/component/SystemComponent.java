package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemModule;
import com.example.chen.wanandroiddemo.di.module.WXModule;
import com.example.chen.wanandroiddemo.ui.fragment.SystemFragment;
import com.example.chen.wanandroiddemo.ui.fragment.WXFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemModule.class)
public interface SystemComponent {
    void inject(SystemFragment systemFragment);

}
