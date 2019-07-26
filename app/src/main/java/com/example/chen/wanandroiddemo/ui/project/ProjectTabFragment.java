package com.example.chen.wanandroiddemo.ui.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ProjectsAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.ProjectTabContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerProjectTabComponent;
import com.example.chen.wanandroiddemo.di.module.ProjectTabModule;
import com.example.chen.wanandroiddemo.presenter.project.ProjectTabPresenter;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.example.chen.wanandroiddemo.utils.JumpUtils;
import com.example.chen.wanandroiddemo.utils.NetUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 20:33
 */
@SuppressLint("ValidFragment")
public class ProjectTabFragment extends BaseRefreshFragment<ProjectTabPresenter> implements ProjectTabContract.View {
    private int curPage = 1;
    private List<Article> mArticles;
    private ProjectsAdapter mProjectsAdapter;

    private Tab mProjectTab;

    public ProjectTabFragment(Tab projectTab) {
        mProjectTab = projectTab;
    }

    @Override
    protected void inject() {
        DaggerProjectTabComponent.builder().projectTabModule(new ProjectTabModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mArticles = new ArrayList<>();
        mProjectsAdapter = new ProjectsAdapter(R.layout.item_projecttab, mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mProjectsAdapter);
        mProjectsAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            JumpUtils.jumpToArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });
    }

    @Override
    public void reLoad() {
        curPage = 1;
        presenter.getProjectTabArticles(curPage++, mProjectTab.getId());
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        curPage = 1;
        presenter.getProjectTabArticles(curPage++, mProjectTab.getId());
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        presenter.getProjectTabArticles(curPage++, mProjectTab.getId());
    }

    @Override
    public void showProjectTabArticles(List<Article> projectTabArticles) {
        if (curPage == 1)
            mArticles.clear();
        mArticles.addAll(projectTabArticles);
        mProjectsAdapter.notifyDataSetChanged();
    }

    @Override
    public String toString() {
        return mProjectTab.getName();
    }
}
