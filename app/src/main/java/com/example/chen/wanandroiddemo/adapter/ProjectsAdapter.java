package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import android.text.Html;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/30 20:03
 */
public class ProjectsAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {

    public ProjectsAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_desc, item.getDesc())
                .setText(R.id.tv_time, item.getNiceDate());
        if (!DataManager.getInstance().getNoImageMode()) {
            Glide.with(mContext)
                    .load(item.getEnvelopePic())
                    .into((ImageView) helper.getView(R.id.iv_pic));
        }
    }
}
