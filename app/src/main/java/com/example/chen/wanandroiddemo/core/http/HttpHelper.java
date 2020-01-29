package com.example.chen.wanandroiddemo.core.http;

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

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 10:51
 */
public interface HttpHelper {

    Observable<BaseResponse<List<Banner>>> getBanner();

    Observable<BaseResponse<PageResponse<Article>>> getArticles(int page);

    Observable<BaseResponse<List<Tab>>> getWxTab();

    Observable<BaseResponse<PageResponse<Article>>> getWxTabArticles(int id, int page);

    Observable<BaseResponse<PageResponse<Article>>> getWxTabSearchArticles(int id, int page, String k);

    Observable<BaseResponse<List<Tab>>> getProjectTab();

    Observable<BaseResponse<PageResponse<Article>>> getProjectTabArticles(int page, int cid);

    Observable<BaseResponse<List<Tab>>> getSystem();

    Observable<BaseResponse<PageResponse<Article>>> getSystemArticles(int page, int cid);

    Observable<BaseResponse<List<Navigation>>> getNavigation();

    Observable<BaseResponse<List<HotWord>>> getHotWord();

    Observable<BaseResponse<PageResponse<Article>>> getSearchArticles(int page, String k);

    Observable<BaseResponse<List<Website>>> getCommonWebsite();

    Observable<BaseResponse<LoginData>> getLoginData(String username, String password);

    Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword);

    Observable<BaseResponse<LoginData>> logout();

    Observable<BaseResponse<PageResponse<CollectionArticle>>> getCollectedArticles(int page);

    Observable<BaseResponse<List<Website>>> getCollectedWebsites();

    Observable<BaseResponse> collectArticle(int id);

    Observable<BaseResponse> collectOutsideArticle(String title, String author, String link);

    Observable<BaseResponse> cancelCollect(int id);

    Observable<BaseResponse> cancelCollect(int id, int originId);

    Observable<BaseResponse> collectWebsite(String name, String link);

    Observable<BaseResponse> editWebsite(int id, String name, String link);

    Observable<BaseResponse> deleteWebsite(int id);

    Observable<BaseResponse<PageResponse<Coin>>> getCoinRanks(int page);

    Observable<BaseResponse<Coin>> getCoin();

    Observable<BaseResponse<PageResponse<CoinRecord>>> getCoinRecords(int page);

    Observable<BaseResponse<PageResponse<ShareArticle>>> getSquareList(int page);

    Observable<BaseResponse<SquareShareArticles>> getPersonalSquare(int id, int page);

    Observable<BaseResponse<SquareShareArticles>> getMySquare(int page);

    Observable<BaseResponse<SquareShareArticles>> deleteShareArticle(int id);

    Observable<BaseResponse> shareArticle(String title, String link);
}
