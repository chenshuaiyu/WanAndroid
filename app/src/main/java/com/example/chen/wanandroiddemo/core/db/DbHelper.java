package com.example.chen.wanandroiddemo.core.db;

import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 10:50
 */
public interface DbHelper {
    void addHistoryRecord(HistoryRecord record);

    List<HistoryRecord> getAllHistoryRecord();

    void clearHistoryRecord();

    void deleteHistoryRecord(HistoryRecord record);

    List<HistoryRecord> getSearchAssociation(String key);
}
