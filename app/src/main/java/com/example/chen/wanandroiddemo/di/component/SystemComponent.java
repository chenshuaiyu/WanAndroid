package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SystemModule;
import com.example.chen.wanandroiddemo.ui.system.SystemFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SystemModule.class)
public interface SystemComponent {
    void inject(SystemFragment systemFragment);

}
