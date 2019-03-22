package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.example.chen.wanandroiddemo.utils.GlideImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


/**
 * Coder : chenshuaiyu
 * Time : 2019/3/17 16:29
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    private List<Banner> mBannerList;
    private List<Article> mArticles;
    private Context mContext;

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_BANNER = 1;

    public HomeAdapter(Context context, List<Banner> bannerList, List<Article> articles) {
        mContext = context;
        mBannerList = bannerList;
        mArticles = articles;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if (i == TYPE_BANNER)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_banner, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_normal, viewGroup, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder homeHolder, final int i) {
        switch (i) {
            case 0:
                ArrayList<String> titles = new ArrayList<>();
                for (Banner banner : mBannerList)
                    titles.add(banner.getTitle());
                setBanner(homeHolder, titles);
                break;
            default:
                setArticles(homeHolder, i - 1);
                break;
        }
        homeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 0) {
                    Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                    intent.putExtra(Constants.ARTICLE_URL, mArticles.get(i - 1).getLink());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void setArticles(HomeHolder homeHolder, int i) {
        Article article = mArticles.get(i);
        homeHolder.author.setText(article.getAuthor());
        homeHolder.superChapterName.setText(article.getSuperChapterName());
        homeHolder.chapterName.setText(article.getChapterName());
        homeHolder.title.setText(article.getTitle());
        homeHolder.time.setText(article.getNiceDate());
    }

    private void setBanner(@NonNull HomeHolder homeHolder, List<String> titles) {
        homeHolder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        homeHolder.mBanner.setImageLoader(new GlideImageLoader());
        homeHolder.mBanner.setImages(mBannerList);
        homeHolder.mBanner.setBannerAnimation(Transformer.DepthPage);
        homeHolder.mBanner.setBannerTitles(titles);
        homeHolder.mBanner.setDelayTime(2500);
        homeHolder.mBanner.start();
    }

    @Override
    public int getItemCount() {
        return mArticles.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_BANNER;
        return TYPE_NORMAL;
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        com.youth.banner.Banner mBanner;
        TextView author;
        TextView superChapterName;
        TextView chapterName;
        TextView title;
        TextView time;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
            author = itemView.findViewById(R.id.author);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            chapterName = itemView.findViewById(R.id.chapterName);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}
