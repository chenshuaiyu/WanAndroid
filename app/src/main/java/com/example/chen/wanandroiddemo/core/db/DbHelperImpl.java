package com.example.chen.wanandroiddemo.core.db;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.dao.DaoSession;

import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 11:45
 */
public class DbHelperImpl implements DbHelper {
    private DaoSession mDaoSession;

    @Inject
    public DbHelperImpl() {
        mDaoSession = WanAndroidApp.getInstance().getDaoSession();
    }

}
