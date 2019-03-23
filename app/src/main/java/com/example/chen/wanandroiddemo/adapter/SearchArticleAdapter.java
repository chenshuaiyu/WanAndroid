package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Article;
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
    public void onBindViewHolder(@NonNull SearchArticleHolder searchArticleHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class SearchArticleHolder extends RecyclerView.ViewHolder {

        public SearchArticleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
