package com.example.chen.wanandroiddemo.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.dao.DaoMaster;
import com.example.chen.wanandroiddemo.core.dao.DaoSession;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 21:20
 */
public class WanAndroidApp extends Application {

    private static WanAndroidApp app;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        // 接入腾讯Bugly
//        CrashReport.initCrashReport(getApplicationContext(), "87a5a6c3e8", true);
        // 接入LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //初始化数据库
        initGreenDao();

        DataManager dataManager = DataManager.getInstance();
        dataManager.setNetState(NetUtil.getNetworkType());
        dataManager.setNightMode(false);
        dataManager.setNoImageMode(false);
        dataManager.setAutoCache(false);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, Constants.DB_NAME);
        SQLiteDatabase database = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public static WanAndroidApp getInstance() {
        return app;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
