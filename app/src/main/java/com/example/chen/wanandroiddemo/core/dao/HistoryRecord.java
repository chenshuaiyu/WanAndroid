package com.example.chen.wanandroiddemo.core.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 11:36
 */
@Entity
public class HistoryRecord {
    @Id(autoincrement = true)
    private long id;
    private long time;
    private String data;

    @Generated(hash = 1309351170)
    public HistoryRecord(long id, long time, String data) {
        this.id = id;
        this.time = time;
        this.data = data;
    }

    @Generated(hash = 725453896)
    public HistoryRecord() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
