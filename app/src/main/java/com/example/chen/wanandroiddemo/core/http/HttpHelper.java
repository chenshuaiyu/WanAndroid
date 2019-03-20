package com.example.chen.wanandroiddemo.core.http;

import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.WXTab;
import com.example.chen.wanandroiddemo.core.bean.WXTabArticles;
import java.util.List;
import io.reactivex.Observable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 10:51
 */
public interface HttpHelper {

    Observable<BaseResponse<List<Banner>>> getBanner();

    Observable<BaseResponse<Articles>> getArticles(int page);

    Observable<BaseResponse<List<WXTab>>> getWXTab();

    Observable<BaseResponse<WXTabArticles>> getWXTabArticles(int id, int page);

}
