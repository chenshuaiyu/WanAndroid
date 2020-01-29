package com.example.chen.wanandroiddemo.core;

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
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.core.db.DbHelper;
import com.example.chen.wanandroiddemo.core.db.DbHelperImpl;
import com.example.chen.wanandroiddemo.core.http.HttpHelper;
import com.example.chen.wanandroiddemo.core.http.HttpHelperImpl;
import com.example.chen.wanandroiddemo.core.prefs.PreferenceHelper;
import com.example.chen.wanandroiddemo.core.prefs.PreferenceHelperImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 10:49
 */
public class DataManager implements DbHelper, HttpHelper, PreferenceHelper {
    private static volatile DataManager sInstance = null;

    private DbHelper mDbHelper;
    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;

    private DataManager(DbHelper dbHelper, HttpHelper httpHelper, PreferenceHelper preferenceHelper) {
        mDbHelper = dbHelper;
        mHttpHelper = httpHelper;
        mPreferenceHelper = preferenceHelper;
    }

    public static DataManager getInstance() {
        if (sInstance == null) {
            synchronized (DataManager.class) {
                if (sInstance == null) {
                    sInstance = new DataManager(
                            DbHelperImpl.getInstance(),
                            HttpHelperImpl.getInstance(),
                            PreferenceHelperImpl.getInstance()
                    );
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<BaseResponse<List<Banner>>> getBanner() {
        return mHttpHelper.getBanner();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getArticles(int page) {
        return mHttpHelper.getArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getWxTab() {
        return mHttpHelper.getWxTab();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getWxTabArticles(int id, int page) {
        return mHttpHelper.getWxTabArticles(id, page);
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getWxTabSearchArticles(int id, int page, String k) {
        return mHttpHelper.getWxTabSearchArticles(id, page, k);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getProjectTab() {
        return mHttpHelper.getProjectTab();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getProjectTabArticles(int page, int cid) {
        return mHttpHelper.getProjectTabArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getSystem() {
        return mHttpHelper.getSystem();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getSystemArticles(int page, int cid) {
        return mHttpHelper.getSystemArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<Navigation>>> getNavigation() {
        return mHttpHelper.getNavigation();
    }

    @Override
    public Observable<BaseResponse<List<HotWord>>> getHotWord() {
        return mHttpHelper.getHotWord();
    }

    @Override
    public Observable<BaseResponse<PageResponse<Article>>> getSearchArticles(int page, String k) {
        return mHttpHelper.getSearchArticles(page, k);
    }

    @Override
    public Observable<BaseResponse<List<Website>>> getCommonWebsite() {
        return mHttpHelper.getCommonWebsite();
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mHttpHelper.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mHttpHelper.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Observable<BaseResponse<PageResponse<CollectionArticle>>> getCollectedArticles(int page) {
        return mHttpHelper.getCollectedArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Website>>> getCollectedWebsites() {
        return mHttpHelper.getCollectedWebsites();
    }

    @Override
    public Observable<BaseResponse> collectArticle(int id) {
        return mHttpHelper.collectArticle(id);
    }

    @Override
    public Observable<BaseResponse> collectOutsideArticle(String title, String author, String link) {
        return mHttpHelper.collectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse> cancelCollect(int id) {
        return mHttpHelper.cancelCollect(id);
    }

    @Override
    public Observable<BaseResponse> cancelCollect(int id, int originId) {
        return mHttpHelper.cancelCollect(id, originId);
    }

    @Override
    public Observable<BaseResponse> collectWebsite(String name, String link) {
        return mHttpHelper.collectWebsite(name, link);
    }

    @Override
    public Observable<BaseResponse> editWebsite(int id, String name, String link) {
        return mHttpHelper.editWebsite(id, name, link);
    }

    @Override
    public Observable<BaseResponse> deleteWebsite(int id) {
        return mHttpHelper.deleteWebsite(id);
    }

    @Override
    public Observable<BaseResponse<PageResponse<Coin>>> getCoinRanks(int page) {
        return mHttpHelper.getCoinRanks(page);
    }

    @Override
    public Observable<BaseResponse<Coin>> getCoin() {
        return mHttpHelper.getCoin();
    }

    @Override
    public Observable<BaseResponse<PageResponse<CoinRecord>>> getCoinRecords(int page) {
        return mHttpHelper.getCoinRecords(page);
    }

    @Override
    public Observable<BaseResponse<PageResponse<ShareArticle>>> getSquareList(int page) {
        return mHttpHelper.getSquareList(page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> getPersonalSquare(int id, int page) {
        return mHttpHelper.getPersonalSquare(id, page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> getMySquare(int page) {
        return mHttpHelper.getMySquare(page);
    }

    @Override
    public Observable<BaseResponse<SquareShareArticles>> deleteShareArticle(int id) {
        return mHttpHelper.deleteShareArticle(id);
    }

    @Override
    public Observable<BaseResponse> shareArticle(String title, String link) {
        return mHttpHelper.shareArticle(title, link);
    }

    @Override
    public void addHistoryRecord(HistoryRecord record) {
        mDbHelper.addHistoryRecord(record);
    }

    @Override
    public List<HistoryRecord> getAllHistoryRecord() {
        return mDbHelper.getAllHistoryRecord();
    }

    @Override
    public void clearHistoryRecord() {
        mDbHelper.clearHistoryRecord();
    }

    @Override
    public void deleteHistoryRecord(HistoryRecord record) {
        mDbHelper.deleteHistoryRecord(record);
    }

    @Override
    public void setLoginStatus(boolean status) {
        mPreferenceHelper.setLoginStatus(status);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setNightMode(boolean mode) {
        mPreferenceHelper.setNightMode(mode);
    }

    @Override
    public boolean getNightMode() {
        return mPreferenceHelper.getNightMode();
    }

    @Override
    public void setNetState(String state) {
        mPreferenceHelper.setNetState(state);
    }

    @Override
    public String getNetState() {
        return mPreferenceHelper.getNetState();
    }

    @Override
    public void setNoImageMode(boolean mode) {
        mPreferenceHelper.setNoImageMode(mode);
    }

    @Override
    public boolean getNoImageMode() {
        return mPreferenceHelper.getNoImageMode();
    }

    @Override
    public void setAutoCache(boolean autoCache) {
        mPreferenceHelper.setAutoCache(autoCache);
    }

    @Override
    public boolean getAutoCache() {
        return mPreferenceHelper.getAutoCache();
    }
}
