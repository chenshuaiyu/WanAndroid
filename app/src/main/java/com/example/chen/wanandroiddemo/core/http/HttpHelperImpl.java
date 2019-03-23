package com.example.chen.wanandroiddemo.core.http;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.core.http.api.Api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 11:54
 */
public class HttpHelperImpl implements HttpHelper {
    private Api mApi;

    @Inject
    public HttpHelperImpl(Api api) {
        mApi = api;
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
}
