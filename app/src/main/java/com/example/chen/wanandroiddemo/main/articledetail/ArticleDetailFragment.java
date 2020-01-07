package com.example.chen.wanandroiddemo.main.articledetail;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.articledetail.contract.ArticleDetailContract;
import com.example.chen.wanandroiddemo.main.articledetail.presenter.ArticleDetailPresenter;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.statelayout_lib.StateLayoutManager;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;

public class ArticleDetailFragment extends BaseFragment<ArticleDetailPresenter> implements ArticleDetailContract.View {

    @BindView(R.id.fl_container)
    FrameLayout mFrameLayout;

    private String url;
    private AgentWeb mAgentWeb;

    @Override
    protected ArticleDetailPresenter getPresenter() {
        return new ArticleDetailPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_article_detail)
                .setOnReLoadListener(this::showContentView)
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary))
                .setMainFrameErrorView(R.layout.default_error, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(url);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setBlockNetworkImage(mPresenter.getNoImageMode());
        if (mPresenter.getAutoCache()) {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDatabaseEnabled(true);
            if (NetUtil.isNetworkConnected()) {
                //默认从网络获取
                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                //若有缓存，使用缓存，否则从网络获取
                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            mSettings.setAppCacheEnabled(false);
            mSettings.setDatabaseEnabled(false);
            //不使用缓存，只从网络获取
            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        //支持缩放
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
