package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
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
        helper.setText(R.id.author, item.getAuthor())
                .setText(R.id.title, item.getTitle())
                .setText(R.id.desc, item.getDesc())
                .setText(R.id.time, item.getNiceDate());
        if (!WanAndroidApp.getInstance().getAppComponent().getDataManager().getNoImageMode()) {
            Glide.with(mContext)
                    .load(item.getEnvelopePic())
                    .into((ImageView) helper.getView(R.id.image_view));
        }
    }
}
