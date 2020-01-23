package com.example.chen.wanandroiddemo.core.http;

import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.base.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.CoinRecord;
import com.example.chen.wanandroiddemo.core.bean.CollectionArticle;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.base.PageResponse;
import com.example.chen.wanandroiddemo.core.bean.ShareArticle;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
import com.example.chen.wanandroiddemo.core.bean.Tab;
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
    public Observable<BaseResponse<PageResponse<Article>>> getArticles(int page) {
        return mApi.getArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getWXTab() {
        return mApi.getWXTab();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getWXTabArticles(int id, int page) {
        return mApi.getWXTabArticles(id, page);
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getWxTabSearchArticles(int id, int page, String k) {
        return mApi.getWxTabSearchArticles(id, page, k);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getProjectTab() {
        return mApi.getProjectTab();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getProjectTabArticles(int page, int cid) {
        return mApi.getProjectTabArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getSystem() {
        return mApi.getSystem();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getSystemArticles(int page, int cid) {
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
    public Observable<BaseResponse<PageResponse<Article>>> getSearchArticles(int page, String k) {
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

    @Override
    public Observable<BaseResponse<PageResponse<CollectionArticle>>> getCollectedArticles(int page) {
        return mApi.getCollectedArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Website>>> getCollectedWebsites() {
        return mApi.getCollectedWebsites();
    }

    @Override
    public Observable<BaseResponse> collectArticle(int id) {
        return mApi.collectArticle(id);
    }

    @Override
    public Observable<BaseResponse> collectOutsideArticle(String title, String author, String link) {
        return mApi.collectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse> cancelCollect(int id) {
        return mApi.cancelCollect(id);
    }

    @Override
    public Observable<BaseResponse> cancelCollect(int id, int originId) {
        return mApi.cancelCollect(id, originId);
    }

    @Override
    public Observable<BaseResponse> collectWebsite(String name, String link) {
        return mApi.collectWebsite(name, link);
    }

    @Override
    public Observable<BaseResponse> editWebsite(int id, String name, String link) {
        return mApi.editWebsite(id, name, link);
    }

    @Override
    public Observable<BaseResponse> deleteWebsite(int id) {
        return mApi.deleteWebsite(id);
    }

    @Override
    public Observable<BaseResponse<PageResponse<Coin>>> getCoinRanks(int page) {
        return mApi.getCoinRanks(page);
    }

    @Override
    public Observable<BaseResponse<Coin>> getCoin() {
        return mApi.getCoin();
    }

    @Override
    public Observable<BaseResponse<PageResponse<CoinRecord>>> getCoinRecords(int page) {
        return mApi.getCoinRecords(page);
    }

    @Override
    public Observable<BaseResponse<PageResponse<ShareArticle>>> getSquareList(int page) {
        return mApi.getSquareList(page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> getPersonalSquare(int id, int page) {
        return mApi.getPersonalSquare(id, page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> getMySquare(int page) {
        return mApi.getMySquare(page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> deleteShareArticle(int id) {
        return mApi.deleteShareArticle(id);
    }

    @Override
    public Observable<BaseResponse> shareArticle(String title, String link) {
        return mApi.shareArticle(title, link);
    }
}
