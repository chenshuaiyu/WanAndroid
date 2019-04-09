package com.example.chen.wanandroiddemo.ui.wx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText search;
    private Button mSearchButton;

    private int mode = NORMAL_MODE;
    private String searchContent = "";

    public static final int NORMAL_MODE = 0;
    public static final int SEARCH_MODE = 1;

    private int curPage = 1;
    private int curSearchPage = 1;
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

        View searview = LayoutInflater.from(getActivity()).inflate(R.layout.common_search_view, null);
        searview.setPadding(5, 0, 5, 0);
        search = searview.findViewById(R.id.edit_text);
        mSearchButton = searview.findViewById(R.id.search_button);

        mArticlesAdapter.addHeaderView(searview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mWXTabArticleList.get(position);
            jumpToDetail(article.getLink(), article.getTitle());
        });
        mSearchButton.setOnClickListener(
                v -> {
                    mode = SEARCH_MODE;
                    curSearchPage = 1;
                    searchContent = search.getText().toString();
                    if (!TextUtils.isEmpty(searchContent))
                        presenter.getWXTabSearchArticles(mWXTab.getId(), curSearchPage, searchContent);
                }
        );

        presenter.getWXTabArticles(mWXTab.getId(), curPage++);
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        mode = NORMAL_MODE;
        curPage = 1;
        curSearchPage = 1;
        search.setText("");
        presenter.getWXTabArticles(mWXTab.getId(), curPage);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        if (mode == NORMAL_MODE)
            presenter.getWXTabArticles(mWXTab.getId(), curPage++);
        else
            presenter.getWXTabSearchArticles(mWXTab.getId(), curSearchPage++, searchContent);
    }

    @Override
    public void showWXTabArticles(List<Article> wxTabArticles) {
        if (curPage == 1)
            mWXTabArticleList.clear();
        mWXTabArticleList.addAll(wxTabArticles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showWXTabSearchArticles(List<Article> wxTabArticles) {
        if (curSearchPage == 1)
            mWXTabArticleList.clear();
        mWXTabArticleList.addAll(wxTabArticles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    private void jumpToDetail(String link, String title) {
        Intent intent = ArticleDetailActivity.newIntent(getActivity(), link, title);
        startActivity(intent);
    }

    @Override
    public String toString() {
        return mWXTab.getName();
    }
}
