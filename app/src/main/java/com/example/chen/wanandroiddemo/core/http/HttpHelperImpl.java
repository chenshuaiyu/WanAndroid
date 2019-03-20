package com.example.chen.wanandroiddemo.core.http;

import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.WXTab;
import com.example.chen.wanandroiddemo.core.bean.WXTabArticles;
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
    public Observable<BaseResponse<List<WXTab>>> getWXTab() {
        return mApi.getWXTab();
    }

    @Override
    public Observable<BaseResponse<WXTabArticles>> getWXTabArticles(int id, int page) {
        return mApi.getWXTabArticles(id, page);
    }


}
