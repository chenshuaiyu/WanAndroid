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
 * Time : 2019/3/20 22:03
 */
public class WXTabAdapter extends RecyclerView.Adapter<WXTabAdapter.WXTabHolder> {
    private Context mContext;
    private List<Article> mWXTabArticleList;

    public WXTabAdapter(Context context, List<Article> wxTabArticleList) {
        mContext = context;
        mWXTabArticleList = wxTabArticleList;
    }

    @NonNull
    @Override
    public WXTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wxtab, viewGroup, false);
        return new WXTabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WXTabHolder wxTabHolder, final int i) {
        setArticles(wxTabHolder, i);
        wxTabHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = mWXTabArticleList.get(i);
                Intent intent = ArticleDetailActivity.newIntent(mContext, article.getLink(), article.getTitle());
                mContext.startActivity(intent);

            }
        });
    }

    private void setArticles(WXTabHolder wxTabHolder, int i) {
        Article article = mWXTabArticleList.get(i);
        wxTabHolder.author.setText(article.getAuthor());
        wxTabHolder.superChapterName.setText(article.getSuperChapterName());
        wxTabHolder.chapterName.setText(article.getChapterName());
        wxTabHolder.title.setText(article.getTitle());
        wxTabHolder.time.setText(article.getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mWXTabArticleList.size();
    }

    class WXTabHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView superChapterName;
        TextView chapterName;
        TextView title;
        TextView time;

        public WXTabHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            chapterName = itemView.findViewById(R.id.chapterName);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}
