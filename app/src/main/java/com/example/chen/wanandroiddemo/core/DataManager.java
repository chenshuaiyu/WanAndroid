package com.example.chen.wanandroiddemo.core;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.WXTab;
import com.example.chen.wanandroiddemo.core.bean.WXTabArticles;
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
    public Observable<BaseResponse<List<WXTab>>> getWXTab() {
        return mHttpHelper.getWXTab();
    }

    @Override
    public Observable<BaseResponse<WXTabArticles>> getWXTabArticles(int id, int page) {
        return mHttpHelper.getWXTabArticles(id, page);
    }
}
