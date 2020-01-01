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
import com.example.chen.wanandroiddemo.core.http.api.Api;
import com.example.chen.wanandroiddemo.core.http.api.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 11:54
 */
public class HttpHelperImpl implements HttpHelper {
    private static volatile HttpHelperImpl sInstance = null;

    private Api mApi;

    public HttpHelperImpl(Api api) {
        mApi = api;
    }

    public static HttpHelper getInstance() {
        if (sInstance == null) {
            synchronized (HttpHelperImpl.class) {
                if (sInstance == null) {
                    sInstance = new HttpHelperImpl(RetrofitClient.createService());
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<BaseResponse<List<Banner>>> getBanner() {
        return mApi.getBanner();
    }

    @Override
    public Observable<BaseResponse<Articles>> getArticles(int page) {
        return mApi.getArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getWXTab() {
        return mApi.getWXTab();
    }

    @Override
    public Observable<BaseResponse<Articles>> getWXTabArticles(int id, int page) {
        return mApi.getWXTabArticles(id, page);
    }

    @Override
    public Observable<BaseResponse<Articles>> getWxTabSearchArticles(int id, int page, String k) {
        return mApi.getWxTabSearchArticles(id, page, k);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getProjectTab() {
        return mApi.getProjectTab();
    }

    @Override
    public Observable<BaseResponse<Articles>> getProjectTabArticles(int page, int cid) {
        return mApi.getProjectTabArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<System>>> getSystem() {
        return mApi.getSystem();
    }

    @Override
    public Observable<BaseResponse<Articles>> getSystemArticles(int page, int cid) {
        return mApi.getSystemArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<Navigation>>> getNavigation() {
        return mApi.getNavigation();
    }

    @Override
    public Observable<BaseResponse<List<HotWord>>> getHotWord() {
        return mApi.getHotWord();
    }

    @Override
    public Observable<BaseResponse<Articles>> getSearchArticles(int page, String k) {
        return mApi.getSearchArticles(page, k);
    }

    @Override
    public Observable<BaseResponse<List<Website>>> getCommonWebsite() {
        return mApi.getCommonWebsite();
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mApi.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mApi.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApi.logout();
    }
}
