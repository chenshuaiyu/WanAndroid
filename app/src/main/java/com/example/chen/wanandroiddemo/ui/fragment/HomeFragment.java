package com.example.chen.wanandroiddemo.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.HomeAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.HomeContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.di.component.DaggerHomeComponent;
import com.example.chen.wanandroiddemo.di.module.HomeModule;
import com.example.chen.wanandroiddemo.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/11 22:25
 */
public class HomeFragment extends BaseRefreshFragment<HomePresenter> implements HomeContract.View {
    private HomeAdapter mHomeAdapter;
    private List<Banner> mBannerList;
    private List<Article> mArticleList;

    private int curPage = 0;

    @Override
    protected void inject() {
        DaggerHomeComponent.builder().homeModule(new HomeModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mBannerList = new ArrayList<>();
        mArticleList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdapter = new HomeAdapter(getActivity(), mBannerList, mArticleList);
        mRecyclerView.setAdapter(mHomeAdapter);

        presenter.getBanner();
        presenter.getArticles(curPage++);

        getActivity().findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 0;
        presenter.getBanner();
        presenter.getArticles(curPage);
        refreshLayout.finishRefresh(1500);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getArticles(curPage++);
        refreshLayout.finishLoadMore(1500);
    }

    @Override
    public void showBanner(List<com.example.chen.wanandroiddemo.core.bean.Banner> banners) {
        if (banners != null && banners.size() != 0) {
            mBannerList.clear();
            mBannerList.addAll(banners);
            mHomeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showArticles(List<Article> articles) {
        if (curPage == 0)
            mArticleList.clear();
        mArticleList.addAll(articles);
        mHomeAdapter.notifyDataSetChanged();
    }
}
