package com.example.chen.wanandroiddemo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author : chenshuaiyu
 * @date : 2019/8/13 18:06
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScoped {
}
