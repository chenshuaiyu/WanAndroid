package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/24 21:13
 */
public class NavigationAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<Navigation> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Navigation item) {
        helper.setText(R.id.tv_title, item.getName());
        TagFlowLayout tagFlowLayout = helper.getView(R.id.tab_flow_layout);
        tagFlowLayout.setAdapter(new TagAdapter<Article>(item.getArticles()) {
            @Override
            public View getView(FlowLayout parent, int position, final Article article) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_tag, parent, false);
                TextView nameTv = view.findViewById(R.id.tv_name);
                nameTv.setText(article.getTitle());
                nameTv.setTextColor(ColorUtil.randomTagColor());
                view.setOnClickListener(v -> OpenActivityUtil.openArticleDetailActivity(mContext, article.getLink(), article.getTitle()));
                return view;
            }
        });
    }
}
