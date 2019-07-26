package com.example.chen.wanandroiddemo.ui.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.HomeContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.di.component.DaggerHomeComponent;
import com.example.chen.wanandroiddemo.di.module.HomeModule;
import com.example.chen.wanandroiddemo.presenter.homepage.HomePresenter;
import com.example.chen.wanandroiddemo.utils.GlideImageLoader;
import com.example.chen.wanandroiddemo.utils.JumpUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 22:25
 */
public class HomeFragment extends BaseRefreshFragment<HomePresenter> implements HomeContract.View {
    private com.youth.banner.Banner mBanner;

    private ArticlesAdapter mArticlesAdapter;
    private List<Banner> mBannerList;
    private List<Article> mArticleList;

    private int curPage = 0;

    @Override
    protected void inject() {
        DaggerHomeComponent.builder().homeModule(new HomeModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mBannerList = new ArrayList<>();
        mArticleList = new ArrayList<>();

        View bannerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_banner, null);
        mBanner = bannerLayout.findViewById(R.id.banner);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticleList);
        mArticlesAdapter.addHeaderView(bannerLayout);
        mRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticleList.get(position);
            JumpUtils.jumpToArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticleList.get(position);

            switch (view.getId()) {
                case R.id.chapter:
                    JumpUtils.jumpToSystemArticlesActivity(getActivity(),
                            article.getSuperChapterName(), article.getChapterName(), article.getChapterId());
                    break;
                case R.id.collect:
                    break;
                default:
                    break;
            }
        });

        getActivity().findViewById(R.id.toolbar).setOnClickListener(v -> mRecyclerView.scrollToPosition(0));
    }

    @Override
    public void reLoad() {
        presenter.getBanner();
        curPage = 0;
        presenter.getArticles(curPage++);
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 0;
        presenter.getBanner();
        presenter.getArticles(curPage++);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getArticles(curPage++);
    }

    @Override
    public void showBanner(List<com.example.chen.wanandroiddemo.core.bean.Banner> banners) {
        if (banners != null && banners.size() != 0) {
            mBannerList.clear();
            mBannerList.addAll(banners);
        }
        setBanner();
    }

    @Override
    public void showArticles(List<Article> articles) {
        if (curPage == 0)
            mArticleList.clear();
        mArticleList.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    private void setBanner() {
        List<String> title = new ArrayList<>();
        for (Banner banner : mBannerList)
            title.add(banner.getTitle());
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(mBannerList);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerTitles(title);
        mBanner.setDelayTime(2500);
        mBanner.setOnBannerListener(position -> {
            Banner banner = mBannerList.get(position);
            JumpUtils.jumpToArticleDetailActivity(getActivity(), banner.getUrl(), banner.getTitle());
        });
        mBanner.start();
    }
}
