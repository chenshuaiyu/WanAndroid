package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.WXTabModule;
import com.example.chen.wanandroiddemo.ui.wx.WxTabFragment;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Component(modules = WXTabModule.class)
public interface WXTabComponent {
    void inject(WxTabFragment wxTabFragment);

}
