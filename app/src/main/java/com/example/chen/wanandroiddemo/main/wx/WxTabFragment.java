package com.example.chen.wanandroiddemo.main.wx;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.wx.contract.WxTabContract;
import com.example.chen.wanandroiddemo.main.wx.presenter.WxTabPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.chen.wanandroiddemo.widget.SearchView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 18:13
 */
public class WxTabFragment extends BaseFragment<WxTabPresenter> implements WxTabContract.View {

    private static final String BUNDLE_WX_TAB = "wx_tab";
    private static final int NORMAL_MODE = 0;
    private static final int SEARCH_MODE = 1;

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private int mode = NORMAL_MODE;
    private String searchContent = "";

    private int curSearchPage = 1;
    private List<Article> mWxTabArticleList = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;

    private Tab mWxTab;
    private SearchView mSearchView;

    @Override
    protected WxTabPresenter getPresenter() {
        return new WxTabPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_refresh_recycler_view)
                .setOnReLoadListener(() -> {
                    curSearchPage = 1;
                    searchContent = "";
                    mRefreshRecyclerView.reLoad();
                })
                .build();
    }

    public static WxTabFragment newInstance(Tab tab) {
        Bundle args = new Bundle();
        args.putSerializable(WxTabFragment.BUNDLE_WX_TAB, tab);
        WxTabFragment fragment = new WxTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        assert getArguments() != null;
        mWxTab = (Tab) getArguments().getSerializable(BUNDLE_WX_TAB);

        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mWxTabArticleList);

        mSearchView = new SearchView(getContext());
        mSearchView.setPadding(5, 0, 5, 0);
        mArticlesAdapter.addHeaderView(mSearchView);

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mWxTabArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mode = NORMAL_MODE;
                mSearchView.clear();
                mPresenter.getWxTabArticles(mWxTab.getId(), firstPage);
            }

            @Override
            public void loadMore(int page) {
                if (mode == NORMAL_MODE) {
                    mPresenter.getWxTabArticles(mWxTab.getId(), page);
                } else {
                    mPresenter.getWxTabSearchArticles(mWxTab.getId(), curSearchPage++, searchContent);
                }
            }
        });

        mSearchView.setCallback(content -> {
            mode = SEARCH_MODE;
            curSearchPage = 1;
            if (!TextUtils.isEmpty(content)) {
                searchContent = content;
                mPresenter.getWxTabSearchArticles(mWxTab.getId(), curSearchPage, content);
            }
        });
    }

    @Override
    public void showWxTabArticles(List<Article> wxTabArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mWxTabArticleList.clear();
        }
        mWxTabArticleList.addAll(wxTabArticles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showWxTabSearchArticles(List<Article> wxTabArticles) {
        if (curSearchPage == 1) {
            curSearchPage++;
            mWxTabArticleList.clear();
        }
        mWxTabArticleList.addAll(wxTabArticles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mWxTabArticleList.get(position).setCollect(true);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mWxTabArticleList.get(position).setCollect(false);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return mWxTab.getName();
    }
}
