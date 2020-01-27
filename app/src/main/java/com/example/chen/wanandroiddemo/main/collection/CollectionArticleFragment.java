package com.example.chen.wanandroiddemo.main.collection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.CollectionArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.CollectionArticle;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionArticleContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionArticlePresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:56
 */
public class CollectionArticleFragment extends BaseFragment<CollectionArticlePresenter> implements CollectionArticleContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<CollectionArticle> mCollectionArticles = new ArrayList<>();
    private CollectionArticlesAdapter mCollectionArticlesAdapter;

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
        mCollectionArticlesAdapter = new CollectionArticlesAdapter(R.layout.item_collection_article, mCollectionArticles);
        mRefreshRecyclerView.setAdapter(mCollectionArticlesAdapter);

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
        mCollectionArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            CollectionArticle article = mCollectionArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(getContext(), article.getLink(), article.getTitle());
        });
        mCollectionArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CollectionArticle collectionArticle = mCollectionArticles.get(position);
            if (view.getId() == R.id.iv_collect) {
                //取消收藏
                new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                        .setTitle(R.string.cancel_collect)
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                            mPresenter.cancelCollect(collectionArticle.getId(), collectionArticle.getOriginId());
                            mCollectionArticles.remove(position);
                            mCollectionArticlesAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })
                        .show();
            }
        });
    }

    @Override
    public void showCollectedArticles(List<CollectionArticle> articles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mCollectionArticles.clear();
        }
        mCollectionArticles.addAll(articles);
        mCollectionArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "文章";
    }
}
