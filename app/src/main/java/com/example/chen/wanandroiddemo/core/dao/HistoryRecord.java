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
    private Long id;
    private Long time;
    private String data;

    @Generated(hash = 83136920)
    public HistoryRecord(Long id, Long time, String data) {
        this.id = id;
        this.time = time;
        this.data = data;
    }

    @Generated(hash = 725453896)
    public HistoryRecord() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
