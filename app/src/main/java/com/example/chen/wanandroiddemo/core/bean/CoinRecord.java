package com.example.chen.wanandroiddemo.core.bean;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 16:35
 */
public class CoinRecord {
    /**
     * coinCount : 36
     * date : 1579745931000
     * desc : 2020-01-23 10:18:51 签到 , 积分：10 + 26
     * id : 137635
     * reason : 签到
     * type : 1
     * userId : 20454
     * userName : ChenSY
     */

    private int coinCount;
    private long date;
    private String desc;
    private int id;
    private String reason;
    private int type;
    private int userId;
    private String userName;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
