package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/9 21:19
 */
public class NetUtils {

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static String getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (wifi != null && wifi.isAvailable()) {
                return Constants.WIFI;
            } else if (mobile != null && mobile.isAvailable()) {
                return Constants.MOBILE;
            } else {
                return Constants.NO_NETWORK;
            }
        } else {
            return Constants.NO_NETWORK;
        }
    }
}
