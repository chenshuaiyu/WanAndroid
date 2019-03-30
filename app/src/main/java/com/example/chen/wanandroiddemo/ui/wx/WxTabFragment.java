package com.example.chen.wanandroiddemo.ui.wx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.WXTabContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerWXTabComponent;
import com.example.chen.wanandroiddemo.di.module.WXTabModule;
import com.example.chen.wanandroiddemo.presenter.WXTabPresenter;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 18:13
 */
@SuppressLint("ValidFragment")
public class WxTabFragment extends BaseRefreshFragment<WXTabPresenter> implements WXTabContract.View {
    private int curPage = 0;
    private List<Article> mWXTabArticleList;
    private ArticlesAdapter mArticlesAdapter;

    private Tab mWXTab;

    public WxTabFragment(Tab WXTab) {
        mWXTab = WXTab;
    }

    @Override
    protected void inject() {
        DaggerWXTabComponent.builder().wXTabModule(new WXTabModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mWXTabArticleList = new ArrayList<>();
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mWXTabArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mWXTabArticleList.get(position);
            jumpToDetail(article.getLink(), article.getTitle());
        });

        presenter.getWXTabArticles(mWXTab.getId(), curPage++);
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 0;
        presenter.getWXTabArticles(mWXTab.getId(), curPage);
        refreshLayout.finishRefresh(1500);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getWXTabArticles(mWXTab.getId(), curPage++);
        refreshLayout.finishLoadMore(1500);
    }

    @Override
    public void showWXTabArticles(List<Article> wxTabArticles) {
        if (curPage == 0)
            mWXTabArticleList.clear();
        mWXTabArticleList.addAll(wxTabArticles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public String toString() {
        return mWXTab.getName();
    }

    private void jumpToDetail(String link, String title) {
        Intent intent = ArticleDetailActivity.newIntent(getActivity(), link, title);
        startActivity(intent);
    }
}
