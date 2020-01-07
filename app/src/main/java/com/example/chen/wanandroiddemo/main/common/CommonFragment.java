package com.example.chen.wanandroiddemo.main.common;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.main.articledetail.ArticleActivity;
import com.example.chen.wanandroiddemo.main.common.contract.CommonFContract;
import com.example.chen.wanandroiddemo.main.common.presenter.CommonFPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.example.statelayout_lib.StateLayoutManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommonFragment extends BaseFragment<CommonFPresenter> implements CommonFContract.View {

    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;

    private List<Website> mWebsites;
    private TagAdapter<Website> mTagAdapter;

    @Override
    protected CommonFPresenter getPresenter() {
        return new CommonFPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_common)
                .setOnReLoadListener(() -> mPresenter.getCommonWebsite())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mWebsites = new ArrayList<>();
        mTagAdapter = new TagAdapter<Website>(mWebsites) {
            @Override
            public View getView(FlowLayout parent, int position, final Website website) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                TextView textView = view.findViewById(R.id.tv_hot_word);
                textView.setText(website.getName());
                view.setBackgroundColor(ColorUtil.randomTagColor());

                view.setOnClickListener(v -> {
                    Intent intent = ArticleActivity.newIntent(getContext(), website.getId(), website.getLink(), website.getName());
                    startActivity(intent);
                });
                return view;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);
    }

    @Override
    public void showCommonWebsite(List<Website> websites) {
        mWebsites.addAll(websites);
        mTagAdapter.notifyDataChanged();
    }
}
