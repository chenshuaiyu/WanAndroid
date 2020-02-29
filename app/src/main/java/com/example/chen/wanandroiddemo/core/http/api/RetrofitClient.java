package com.example.chen.wanandroiddemo.core.http.api;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author chenshuaiyu
 */
public class RetrofitClient {

    private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            //错误重连
            .retryOnConnectionFailure(true)
            //设置超时
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            //设置Cookie
            .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(WanAndroidApp.getInstance())))
            .build();

    private static Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static Api createService() {
        return mRetrofit.create(Api.class);
    }
}
