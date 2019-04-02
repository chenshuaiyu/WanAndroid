package com.example.chen.wanandroiddemo.core.http;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.core.bean.Website;

import java.util.List;

import io.reactivex.Observable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 10:51
 */
public interface HttpHelper {

    Observable<BaseResponse<List<Banner>>> getBanner();

    Observable<BaseResponse<Articles>> getArticles(int page);

    Observable<BaseResponse<List<Tab>>> getWXTab();

    Observable<BaseResponse<Articles>> getWXTabArticles(int id, int page);

    Observable<BaseResponse<List<Tab>>> getProjectTab();

    Observable<BaseResponse<Articles>> getProjectTabArticles(int page, int cid);

    Observable<BaseResponse<List<System>>> getSystem();

    Observable<BaseResponse<Articles>> getSystemArticles(int page, int cid);

    Observable<BaseResponse<List<Navigation>>> getNavigation();

    Observable<BaseResponse<List<HotWord>>> getHotWord();

    Observable<BaseResponse<Articles>> getSearchArticles(int page, String k);

    Observable<BaseResponse<List<Website>>> getCommonWebsite();

    Observable<BaseResponse<LoginData>> getLoginData(String username, String password);

    Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword);

    Observable<BaseResponse<LoginData>> logout();
}
