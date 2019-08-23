package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.scope.MyScoped;
import com.example.chen.wanandroiddemo.di.module.ProjectTabModule;
import com.example.chen.wanandroiddemo.ui.project.ProjectTabFragment;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = ProjectTabModule.class)
public interface ProjectTabComponent {
    void inject(ProjectTabFragment projectTabFragment);

}
