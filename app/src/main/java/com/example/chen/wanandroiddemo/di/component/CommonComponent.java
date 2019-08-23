package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.scope.MyScoped;
import com.example.chen.wanandroiddemo.di.module.CommonModule;
import com.example.chen.wanandroiddemo.ui.activity.CommonActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = CommonModule.class)
public interface CommonComponent {
    void inject(CommonActivity commonActivity);

}
