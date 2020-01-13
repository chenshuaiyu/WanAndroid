package com.example.chen.wanandroiddemo.core.http.api;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.CoinRanks;
import com.example.chen.wanandroiddemo.core.bean.CoinRecords;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.SquareArticles;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
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
 * @author : chenshuaiyu
 * @date : 2019/3/16 14:34
 */
public interface Api {

    /**
     * 首页Banner
     * https://www.wanandroid.com/banner/json
     *
     * @return 首页Banner数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<Banner>>> getBanner();

    /**
     * 首页文章
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param page
     * @return 首页文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<Articles>> getArticles(@Path("page") int page);

    /**
     * 公众号Tab
     * https://www.wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号Tab数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<Tab>>> getWXTab();

    /**
     * 公众号文章
     * https://wanandroid.com/wxarticle/list/408/1/json
     *
     * @param id
     * @param page
     * @return 公众号文章数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<Articles>> getWXTabArticles(@Path("id") int id, @Path("page") int page);


    /**
     * 搜索指定公众号文章
     * http://wanandroid.com/wxarticle/list/405/1/json?k=Java
     *
     * @param id
     * @param page
     * @param k
     * @return 公众号文章数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<Articles>> getWxTabSearchArticles(@Path("id") int id, @Path("page") int page, @Query("k") String k);

    /**
     * 项目Tab
     * https://wanandroid.com/project/tree/json
     *
     * @return 项目Tab数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<Tab>>> getProjectTab();

    /**
     * 项目文章
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page
     * @param cid
     * @return 项目文章数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<Articles>> getProjectTabArticles(@Path("page") int page, @Query("cid") int cid);

    /**
     * 体系
     * https://www.wanandroid.com/tree/json
     *
     * @return 体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<System>>> getSystem();

    /**
     * 知识体系下文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param page
     * @param cid
     * @return 知识体系下文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<Articles>> getSystemArticles(@Path("page") int page, @Query("cid") int cid);

    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<Navigation>>> getNavigation();

    /**
     * 搜索热词
     * https://www.wanandroid.com//hotkey/json
     *
     * @return 搜索热词数据
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotWord>>> getHotWord();

    /**
     * 搜索文章
     * https://www.wanandroid.com/article/query/0/json
     *
     * @param page
     * @param k
     * @return 搜索文章数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<Articles>> getSearchArticles(@Path("page") int page, @Field("k") String k);

    /**
     * 常用网站
     * https://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse<List<Website>>> getCommonWebsite();

    /**
     * 登录
     * https://www.wanandroid.com/user/login
     *
     * @param username
     * @param password
     * @return 登录数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     * https://www.wanandroid.com/user/register
     *
     * @param username
     * @param password
     * @param repassword
     * @return 登录数据
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     *
     * @return 登录数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * https://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse> collectArtcile(@Path("id") int id);

    /**
     * 获取积分排行榜
     * https://www.wanandroid.com/coin/rank/1/json
     *
     * @return
     */
    @GET("coin/rank/{page}/json")
    Observable<BaseResponse<CoinRanks>> getCoinRanks(@Path("page") int page);

    /**
     * 获取个人积分
     * https://www.wanandroid.com/lg/coin/userinfo/json
     *
     * @return
     */
    @GET("lg/coin/userinfo/json")
    Observable<BaseResponse<Coin>> getCoin();

    /**
     * 获取个人积分获取列表
     * https://www.wanandroid.com/lg/coin/list/1/json
     *
     * @return
     */
    @GET("lg/coin/list/{page}/json")
    Observable<BaseResponse<CoinRecords>> getCoinRecords(@Path("page") int page);

    /**
     * 获取广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     *
     * @return
     */
    @GET("user_article/list/{page}/json")
    Observable<BaseResponse<SquareArticles>> getSquareList(@Path("page") int page);

    /**
     * 获取分享人列表数据
     * https://www.wanandroid.com/user/2/share_articles/1/json
     *
     * @return
     */
    @GET("user/{id}/share_articles/{page}/json")
    Observable<BaseResponse<SquareShareArticles>> getPresonalSquare(@Path("id") int id, @Path("page") int page);
}
