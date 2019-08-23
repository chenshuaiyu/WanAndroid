package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.scope.MyScoped;
import com.example.chen.wanandroiddemo.di.module.ProjectModule;
import com.example.chen.wanandroiddemo.ui.project.ProjectFragment;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = ProjectModule.class)
public interface ProjectComponent {
    void inject(ProjectFragment projectFragment);

}
