package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.MainActivityModule;
import com.example.chen.wanandroiddemo.ui.activity.MainActivity;
import javax.inject.Singleton;
import dagger.Component;


/**
 * Coder : chenshuaiyu
 * Time : 2019/3/17 10:31
 */
@Singleton
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

}
