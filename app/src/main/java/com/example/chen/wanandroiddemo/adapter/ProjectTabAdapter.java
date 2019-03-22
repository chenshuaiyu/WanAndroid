package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 10:12
 */
public class ProjectTabAdapter extends RecyclerView.Adapter<ProjectTabAdapter.ProjectTabHolder> {
    private Context mContext;
    private List<Article> mArticles;

    public ProjectTabAdapter(Context context, List<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @NonNull
    @Override
    public ProjectTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_projecttab, viewGroup, false);

        return new ProjectTabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectTabHolder projectTabHolder, int i) {
        Article article = mArticles.get(i);

        Log.d("CCC", article.getAuthor());

        Glide.with(mContext).load(article.getEnvelopePic()).into(projectTabHolder.image);
        projectTabHolder.author.setText(article.getAuthor());
        projectTabHolder.title.setText(article.getTitle());
        projectTabHolder.desc.setText(article.getDesc());
        projectTabHolder.time.setText(article.getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ProjectTabHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView author;
        TextView title;
        TextView desc;
        TextView time;

        public ProjectTabHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            time = itemView.findViewById(R.id.time);
        }
    }
}
