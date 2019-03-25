package com.example.chen.wanandroiddemo.core;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.core.db.DbHelper;
import com.example.chen.wanandroiddemo.core.db.DbHelperImpl;
import com.example.chen.wanandroiddemo.core.http.HttpHelper;
import com.example.chen.wanandroiddemo.core.http.HttpHelperImpl;
import com.example.chen.wanandroiddemo.core.prefs.PreferenceHelper;
import com.example.chen.wanandroiddemo.core.prefs.PreferenceHelperImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 10:49
 */
public class DataManager implements DbHelper, HttpHelper, PreferenceHelper {
    private DbHelperImpl mDbHelper;
    private HttpHelperImpl mHttpHelper;
    private PreferenceHelperImpl mPreferenceHelper;

    @Inject
    public DataManager(DbHelperImpl dbHelper, HttpHelperImpl httpHelper, PreferenceHelperImpl preferenceHelper) {
        mDbHelper = dbHelper;
        mHttpHelper = httpHelper;
        mPreferenceHelper = preferenceHelper;
    }

    @Override
    public Observable<BaseResponse<List<Banner>>> getBanner() {
        return mHttpHelper.getBanner();
    }

    @Override
    public Observable<BaseResponse<Articles>> getArticles(int page) {
        return mHttpHelper.getArticles(page);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getWXTab() {
        return mHttpHelper.getWXTab();
    }

    @Override
    public Observable<BaseResponse<Articles>> getWXTabArticles(int id, int page) {
        return mHttpHelper.getWXTabArticles(id, page);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getProjectTab() {
        return mHttpHelper.getProjectTab();
    }

    @Override
    public Observable<BaseResponse<Articles>> getProjectTabArticles(int page, int cid) {
        return mHttpHelper.getProjectTabArticles(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<System>>> getSystem() {
        return mHttpHelper.getSystem();
    }

    @Override
    public Observable<BaseResponse<Articles>> getSystemArticles(int page, int cid) {
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
    public Observable<BaseResponse<Articles>> getSearchArticles(int page, String k) {
        return mHttpHelper.getSearchArticles(page, k);
    }

    @Override
    public Observable<BaseResponse<List<Website>>> getCommonWebsite() {
        return mHttpHelper.getCommonWebsite();
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
}
