package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.MainActivityModule;
import com.example.chen.wanandroiddemo.ui.activity.MainActivity;

import dagger.Component;


/**
 * @author : chenshuaiyu
 * @date : 2019/3/17 10:31
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

}
