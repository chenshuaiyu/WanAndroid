package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.scope.MyScoped;
import com.example.chen.wanandroiddemo.di.module.SystemModule;
import com.example.chen.wanandroiddemo.ui.system.SystemFragment;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = SystemModule.class)
public interface SystemComponent {
    void inject(SystemFragment systemFragment);

}
