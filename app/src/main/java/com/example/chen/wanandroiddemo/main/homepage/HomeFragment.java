package com.example.chen.wanandroiddemo.main.homepage;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.main.homepage.contract.HomeContract;
import com.example.chen.wanandroiddemo.main.homepage.presenter.HomePresenter;
import com.example.chen.wanandroiddemo.utils.GlideImageLoader;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 22:25
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private com.youth.banner.Banner mBanner;

    private List<Banner> mBannerList = new ArrayList<>();
    private List<Article> mArticleList = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_refresh_recycler_view)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        View bannerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_banner, null);
        mBanner = bannerLayout.findViewById(R.id.banner);

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mArticlesAdapter = new ArticlesAdapter(R.layout.common_item_article, mArticleList);
        mArticlesAdapter.addHeaderView(bannerLayout);
        mRefreshRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticleList.get(position);
            switch (view.getId()) {
                case R.id.ll_chapter:
                    OpenActivityUtil.openSystemArticlesActivity(Objects.requireNonNull(getActivity()),
                            article.getSuperChapterName(), article.getChapterName(), article.getChapterId());
                    break;
                case R.id.iv_collect:
                    if (!article.isCollect()) {
                        mPresenter.collectArticle(article.getId(), position);
                    } else {
                        mPresenter.cancelCollectArticle(article.getId(), position);
                    }
                    break;
                default:
                    break;
            }
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getBanner();
                mPresenter.getArticles(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getArticles(page);
            }
        });
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
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mArticleList.clear();
        }
        mArticleList.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mArticleList.get(position).setCollect(true);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mArticleList.get(position).setCollect(false);
            mArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    private void setBanner() {
        List<String> title = new ArrayList<>();
        for (Banner banner : mBannerList) {
            title.add(Html.fromHtml(banner.getTitle()).toString());
        }
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(mBannerList);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerTitles(title);
        mBanner.setDelayTime(2500);
        mBanner.setOnBannerListener(position -> {
            Banner banner = mBannerList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), banner.getUrl(), banner.getTitle());
        });
        mBanner.start();
    }
}
