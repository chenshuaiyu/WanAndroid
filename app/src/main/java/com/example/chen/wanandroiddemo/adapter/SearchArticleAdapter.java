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
 * Time : 2019/3/23 10:14
 */
public class SearchArticleAdapter extends RecyclerView.Adapter<SearchArticleAdapter.SearchArticleHolder> {
    private List<Article> mArticles;
    private Context mContext;

    public SearchArticleAdapter(Context context, List<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @NonNull
    @Override
    public SearchArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search_article, viewGroup, false);
        return new SearchArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchArticleHolder searchArticleHolder, final int i) {
        setArticles(searchArticleHolder, i);
        searchArticleHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = mArticles.get(i - 1);
                Intent intent = ArticleDetailActivity.newIntent(mContext, article.getLink(), article.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    private void setArticles(SearchArticleHolder holder, int i) {
        Article article = mArticles.get(i);
        holder.author.setText(article.getAuthor());
        holder.superChapterName.setText(article.getSuperChapterName());
        holder.chapterName.setText(article.getChapterName());
        holder.title.setText(article.getTitle());
        holder.time.setText(article.getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class SearchArticleHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView superChapterName;
        TextView chapterName;
        TextView title;
        TextView time;

        public SearchArticleHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            chapterName = itemView.findViewById(R.id.chapterName);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}
