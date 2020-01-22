package com.example.chen.wanandroiddemo.core.bean;

/**
 * @author chenshuaiyu
 */
public class SquareShareArticles {
    private Coininfo coinInfo;
    private PageResponse<ShareArticle> shareArticles;

    public Coininfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(Coininfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public PageResponse<ShareArticle> getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(PageResponse<ShareArticle> shareArticles) {
        this.shareArticles = shareArticles;
    }
}
