package com.example.chen.wanandroiddemo.core.db;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.dao.DaoSession;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import java.util.List;
import javax.inject.Inject;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 11:45
 */
public class DbHelperImpl implements DbHelper {
    private DaoSession mDaoSession;

    @Inject
    public DbHelperImpl() {
        mDaoSession = WanAndroidApp.getInstance().getDaoSession();
    }

    @Override
    public void addHistoryRecord(HistoryRecord record) {
        mDaoSession.insert(record);
    }

    @Override
    public List<HistoryRecord> getAllHistoryRecord() {
        List<HistoryRecord> records = mDaoSession.loadAll(HistoryRecord.class);
        return records;
    }

    @Override
    public void clearHistoryRecord() {
        mDaoSession.deleteAll(HistoryRecord.class);
    }

    @Override
    public void deleteHistoryRecord(HistoryRecord record) {
        mDaoSession.delete(record);
    }
}
