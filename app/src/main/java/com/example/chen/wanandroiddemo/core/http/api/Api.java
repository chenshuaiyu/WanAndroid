package com.example.chen.wanandroiddemo.core.http.api;

import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.CoinRecord;
import com.example.chen.wanandroiddemo.core.bean.CollectionArticle;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.ShareArticle;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.core.bean.base.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.base.PageResponse;

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
    Observable<BaseResponse<PageResponse<Article>>> getArticles(@Path("page") int page);

    /**
     * 公众号Tab
     * https://www.wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号Tab数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<Tab>>> getWxTab();

    /**
     * 公众号文章
     * https://wanandroid.com/wxarticle/list/408/1/json
     *
     * @param id
     * @param page
     * @return 公众号文章数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<PageResponse<Article>>> getWxTabArticles(@Path("id") int id, @Path("page") int page);


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
    Observable<BaseResponse<PageResponse<Article>>> getWxTabSearchArticles(@Path("id") int id, @Path("page") int page, @Query("k") String k);

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
    Observable<BaseResponse<PageResponse<Article>>> getProjectTabArticles(@Path("page") int page, @Query("cid") int cid);

    /**
     * 体系
     * https://www.wanandroid.com/tree/json
     *
     * @return 体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<Tab>>> getSystem();

    /**
     * 知识体系下文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param page
     * @param cid
     * @return 知识体系下文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<PageResponse<Article>>> getSystemArticles(@Path("page") int page, @Query("cid") int cid);

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
    Observable<BaseResponse<PageResponse<Article>>> getSearchArticles(@Path("page") int page, @Field("k") String k);

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
     * 收藏文章列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<PageResponse<CollectionArticle>>> getCollectedArticles(@Path("page") int page);

    /**
     * 收藏网站列表
     * https://www.wanandroid.com/lg/collect/usertools/json
     *
     * @return
     */
    @GET("lg/collect/usertools/json")
    Observable<BaseResponse<List<Website>>> getCollectedWebsites();

    /**
     * 收藏文章
     * https://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse> collectArticle(@Path("id") int id);

    /**
     * 收藏站外文章
     * https://www.wanandroid.com/lg/collect/add/json
     *
     * @param title
     * @param author
     * @param link
     * @return
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse> collectOutsideArticle(@Field("title") String title, @Field("author") String author, @Field("link") String link);

    /**
     * 取消收藏 文章列表
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse> cancelCollect(@Path("id") int id);

    /**
     * 取消收藏 我的收藏
     * https://www.wanandroid.com/lg/uncollect/2805/json
     *
     * @param id
     * @param originId
     * @return
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse> cancelCollect(@Path("id") int id, @Field("originId") int originId);

    /**
     * 收藏网站
     * https://www.wanandroid.com/lg/collect/addtool/json
     *
     * @param name
     * @param link
     * @return
     */
    @POST("lg/collect/addtool/json")
    @FormUrlEncoded
    Observable<BaseResponse> collectWebsite(@Field("name") String name, @Field("link") String link);

    /**
     * 编辑收藏网站
     * https://www.wanandroid.com/lg/collect/updatetool/json
     *
     * @param id
     * @param name
     * @param link
     * @return
     */
    @POST("lg/collect/updatetool/json")
    @FormUrlEncoded
    Observable<BaseResponse> editWebsite(@Field("id") int id, @Field("name") String name, @Field("link") String link);

    /**
     * 删除收藏网站
     * https://www.wanandroid.com/lg/collect/deletetool/json
     *
     * @param id
     * @return
     */
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    Observable<BaseResponse> deleteWebsite(@Field("id") int id);

    /**
     * 获取积分排行榜
     * https://www.wanandroid.com/coin/rank/1/json
     *
     * @param page
     * @return
     */
    @GET("coin/rank/{page}/json")
    Observable<BaseResponse<PageResponse<Coin>>> getCoinRanks(@Path("page") int page);

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
     * @param page
     * @return
     */
    @GET("lg/coin/list/{page}/json")
    Observable<BaseResponse<PageResponse<CoinRecord>>> getCoinRecords(@Path("page") int page);

    /**
     * 获取广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     *
     * @param page
     * @return
     */
    @GET("user_article/list/{page}/json")
    Observable<BaseResponse<PageResponse<ShareArticle>>> getSquareList(@Path("page") int page);

    /**
     * 获取分享人列表数据
     * https://www.wanandroid.com/user/2/share_articles/1/json
     *
     * @param id
     * @param page
     * @return
     */
    @GET("user/{id}/share_articles/{page}/json")
    Observable<BaseResponse<SquareShareArticles>> getPersonalSquare(@Path("id") int id, @Path("page") int page);

    /**
     * 获取自己分享列表数据
     * https://wanandroid.com/user/lg/private_articles/1/json
     *
     * @param page
     * @return
     */
    @GET("user/lg/private_articles/{page}/json")
    Observable<BaseResponse<SquareShareArticles>> getMySquare(@Path("page") int page);

    /**
     * 删除自己分享的文章
     * https://wanandroid.com/lg/user_article/delete/9475/json
     *
     * @param id
     * @return
     */
    @POST("lg/user_article/delete/{id}/json")
    Observable<BaseResponse<SquareShareArticles>> deleteShareArticle(@Path("id") int id);

    /**
     * 分享文章
     * https://www.wanandroid.com/lg/user_article/add/json
     *
     * @param title
     * @param link
     * @return
     */
    @POST("lg/collect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse> shareArticle(@Field("title") String title, @Field("link") String link);
}
