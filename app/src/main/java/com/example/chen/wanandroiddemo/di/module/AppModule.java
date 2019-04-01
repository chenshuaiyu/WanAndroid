package com.example.chen.wanandroiddemo.di.module;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.http.api.Api;
import com.example.chen.wanandroiddemo.core.http.cookie.CookieManger;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 10:38
 */
@Module
public class AppModule {
    private WanAndroidApp mApp;

    public AppModule(WanAndroidApp app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        //错误重连
        builder.retryOnConnectionFailure(true);

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //设置Cookie
        builder.cookieJar(new CookieManger(mApp));

        return builder.build();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

}
