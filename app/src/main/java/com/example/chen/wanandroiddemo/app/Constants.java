package com.example.chen.wanandroiddemo.app;

import android.graphics.Color;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 11:12
 */
public class Constants {

    /**
     * Base URL
     */
    public static final String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 状态码
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * Preferences
     */
    public static final String SHAREDPREFERENCES_NAME = "prefs";
    public static final String LOGIN_STATUS = "login_status";
    public static final String LOGIN_ACCOUNT = "login_account";
    public static final String LOGIN_PASSWORD = "login_password";
    public static final String NIGHT_MODE = "night_mode";
    public static final String NET_STATE = "net_state";
    public static final String NO_IMAGE_MODE = "no_image_mode";
    public static final String AUTO_CACHE = "auto_cache";

    /**
     * db
     */
    public static final String DB_NAME = "wanandroid.db";

    /**
     * Intent key
     */
    public static final String ARTICLE_URL = "article_url";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String SYSTEM = "system_id";
    public static final String SEARCH_KEY = "search_key";

    /**
     * BaseLoadActivity ViewState
     */
    public static final int NORMAL_VIEW_STATE = 0;
    public static final int ERROR_VIEW_STATE = 1;
    public static final int LOADING_VIEW_STATE = 2;

    /**
     * Color
     */
    public static final int[] COLOR_HOTWORD = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    /**
     * Network state
     */
    public static final String WIFI = "正在使用WIFI";
    public static final String MOBILE = "正在使用数据网络";
    public static final String NO_NETWORK = "当前无网络";

    /**
     * 邮箱地址
     */
    public static final String EMAIL_ADDRESS = "664251867@qq.com";
}
