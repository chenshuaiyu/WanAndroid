package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.CommonContract;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.di.component.DaggerCommonComponent;
import com.example.chen.wanandroiddemo.di.module.CommonModule;
import com.example.chen.wanandroiddemo.presenter.CommonPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommonActivity extends BaseActivity<CommonPresenter> implements CommonContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;

    private List<Website> mWebsites;
    private TagAdapter<Website> mTagAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    protected void inject() {
        DaggerCommonComponent.builder().commonModule(new CommonModule()).build().inject(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mToolbar.setTitle(getResources().getString(R.string.commom_website));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mWebsites = new ArrayList<>();
        mTagAdapter = new TagAdapter<Website>(mWebsites) {
            @Override
            public View getView(FlowLayout parent, int position, final Website website) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                TextView textView = view.findViewById(R.id.text_view);
                textView.setText(website.getName());
                view.setBackgroundColor(ColorUtils.randomTagColor());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = ArticleDetailActivity.newIntent(CommonActivity.this, website.getLink(), website.getName());
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);

        presenter.getCommonWebsite();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showCommonWebsite(List<Website> websites) {
        mWebsites.addAll(websites);
        mTagAdapter.notifyDataChanged();
    }
}
