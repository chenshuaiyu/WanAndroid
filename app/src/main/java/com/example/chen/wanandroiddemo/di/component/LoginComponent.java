package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.LoginActivityModule;
import com.example.chen.wanandroiddemo.ui.activity.LoginActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 11:51
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = LoginActivityModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
