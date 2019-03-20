package com.example.chen.wanandroiddemo.core.http.api;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.WXTab;
import com.example.chen.wanandroiddemo.core.bean.WXTabArticles;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 14:34
 */
public interface Api {

    @GET("banner/json")
    Observable<BaseResponse<List<Banner>>> getBanner();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<Articles>> getArticles(@Path("page") int page);

    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WXTab>>> getWXTab();

    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<WXTabArticles>> getWXTabArticles(@Path("id") int id, @Path("page") int page);

}
