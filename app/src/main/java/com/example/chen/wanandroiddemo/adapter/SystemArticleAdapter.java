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
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 20:57
 */
public class SystemArticleAdapter extends RecyclerView.Adapter<SystemArticleAdapter.SystemArticleHolder> {
    private Context mContext;
    private List<Article> mArticles;

    public SystemArticleAdapter(Context context, List<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @NonNull
    @Override
    public SystemArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_system_article, viewGroup, false);
        return new SystemArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemArticleHolder systemArticleHolder, final int i) {
        setArticles(systemArticleHolder, i);
        systemArticleHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = mArticles.get(i - 1);
                Intent intent = ArticleDetailActivity.newIntent(mContext, article.getLink(), article.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    private void setArticles(SystemArticleHolder systemArticleHolder, int i) {
        Article article = mArticles.get(i);
        systemArticleHolder.author.setText(article.getAuthor());
        systemArticleHolder.superChapterName.setText(article.getSuperChapterName());
        systemArticleHolder.chapterName.setText(article.getChapterName());
        systemArticleHolder.title.setText(article.getTitle());
        systemArticleHolder.time.setText(article.getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class SystemArticleHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView superChapterName;
        TextView chapterName;
        TextView title;
        TextView time;

        public SystemArticleHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            chapterName = itemView.findViewById(R.id.chapterName);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}
