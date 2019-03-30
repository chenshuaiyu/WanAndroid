package com.example.chen.wanandroiddemo.core.http.api;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.core.bean.Website;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<BaseResponse<List<Tab>>> getWXTab();

    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<Articles>> getWXTabArticles(@Path("id") int id, @Path("page") int page);

    @GET("project/tree/json")
    Observable<BaseResponse<List<Tab>>> getProjectTab();

    @GET("project/list/{page}/json")
    Observable<BaseResponse<Articles>> getProjectTabArticles(@Path("page") int page, @Query("cid") int cid);

    @GET("tree/json")
    Observable<BaseResponse<List<System>>> getSystem();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<Articles>> getSystemArticles(@Path("page") int page, @Query("cid") int cid);

    @GET("navi/json")
    Observable<BaseResponse<List<Navigation>>> getNavigation();

    @GET("hotkey/json")
    Observable<BaseResponse<List<HotWord>>> getHotWord();

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<Articles>> getSearchArticles(@Path("page") int page, @Field("k") String k);

    @GET("friend/json")
    Observable<BaseResponse<List<Website>>> getCommonWebsite();

//    @POST("user/login")
//    @FormUrlEncoded
//    Observable<BaseResponse<Login>> getLoginData(@Field("username") String username, @Field("password") String password);
//
//    @POST("user/register")
//    @FormUrlEncoded
//    Observable<BaseResponse<Login>> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

}
