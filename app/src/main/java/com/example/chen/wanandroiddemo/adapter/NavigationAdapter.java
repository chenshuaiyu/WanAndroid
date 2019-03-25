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
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/24 21:13
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {
    private Context mContext;
    private List<Navigation> mNavigations;

    public NavigationAdapter(Context context, List<Navigation> navigations) {
        mContext = context;
        mNavigations = navigations;
    }

    @NonNull
    @Override
    public NavigationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_navigation, viewGroup, false);
        return new NavigationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationHolder navigationHolder, final int i) {
        Navigation navigation = mNavigations.get(i);
        navigationHolder.title.setText(navigation.getName());
        navigationHolder.tagFlowLayout.setAdapter(new TagAdapter<Article>(navigation.getArticles()) {
            @Override
            public View getView(FlowLayout parent, int position, final Article article) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_tag, parent, false);
                TextView name = view.findViewById(R.id.name);
                name.setText(article.getTitle());
                name.setTextColor(ColorUtil.randomTagColor());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = ArticleDetailActivity.newIntent(mContext, article.getLink(), article.getTitle());
                        mContext.startActivity(intent);
                    }
                });
                return view;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNavigations.size();
    }

    class NavigationHolder extends RecyclerView.ViewHolder{
        TextView title;
        TagFlowLayout tagFlowLayout;

        public NavigationHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tagFlowLayout = itemView.findViewById(R.id.tab_flow_layout);
        }
    }
}
