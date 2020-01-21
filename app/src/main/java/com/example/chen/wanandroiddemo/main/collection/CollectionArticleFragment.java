package com.example.chen.wanandroiddemo.main.collection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionArticleContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionArticlePresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:56
 */
public class CollectionArticleFragment extends BaseFragment<CollectionArticlePresenter> implements CollectionArticleContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<Article> mArticles = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected CollectionArticlePresenter getPresenter() {
        return new CollectionArticlePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_collection_article)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticles);
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getCollectedArticles(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getCollectedArticles(page);
            }
        });
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(getContext(), article.getId(), article.getLink(), article.getTitle(), true);
        });
    }

    @Override
    public void showCollectedArticles(List<Article> articles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mArticles.clear();
        }
        mArticles.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "文章";
    }
}
