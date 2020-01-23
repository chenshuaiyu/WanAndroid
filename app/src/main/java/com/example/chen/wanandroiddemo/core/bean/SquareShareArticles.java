package com.example.chen.wanandroiddemo.core.bean;

import com.example.chen.wanandroiddemo.core.bean.base.PageResponse;

/**
 * @author chenshuaiyu
 */
public class SquareShareArticles {
    private Coin coinInfo;
    private PageResponse<ShareArticle> shareArticles;

    public Coin getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(Coin coinInfo) {
        this.coinInfo = coinInfo;
    }

    public PageResponse<ShareArticle> getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(PageResponse<ShareArticle> shareArticles) {
        this.shareArticles = shareArticles;
    }
}
