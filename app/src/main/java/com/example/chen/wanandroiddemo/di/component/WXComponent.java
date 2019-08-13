package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.WXModule;
import com.example.chen.wanandroiddemo.ui.wx.WXFragment;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = WXModule.class)
public interface WXComponent {
    void inject(WXFragment wxFragment);

}
