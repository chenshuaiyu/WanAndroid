package com.example.chen.wanandroiddemo.ui.fragment;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ProjectTabAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.ProjectTabContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerProjectTabComponent;
import com.example.chen.wanandroiddemo.di.module.ProjectTabModule;
import com.example.chen.wanandroiddemo.presenter.ProjectTabPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 20:33
 */
@SuppressLint("ValidFragment")
public class ProjectTabFragment extends BaseFragment<ProjectTabPresenter> implements ProjectTabContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int curPage = 1;
    private List<Article> mArticles;
    private ProjectTabAdapter mProjectTabAdapter;

    private Tab mProjectTab;

    public ProjectTabFragment(Tab projectTab) {
        mProjectTab = projectTab;
    }

    public Tab getProjectTab() {
        return mProjectTab;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_refresh_recycler_view;
    }

    @Override
    protected void inject() {
        DaggerProjectTabComponent.builder().projectTabModule(new ProjectTabModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mArticles = new ArrayList<>();
        mProjectTabAdapter = new ProjectTabAdapter(getActivity(), mArticles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mProjectTabAdapter);

        presenter.getProjectTabArticles(curPage++, mProjectTab.getId());

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 1;
                presenter.getProjectTabArticles(curPage++, mProjectTab.getId());
                refreshLayout.finishRefresh(1500);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getProjectTabArticles(curPage++, mProjectTab.getId());
                refreshLayout.finishLoadMore(1500);
            }
        });
    }

    @Override
    public void showProjectTabArticles(List<Article> projectTabArticles) {
        if (curPage == 1)
            mArticles.clear();
        mArticles.addAll(projectTabArticles);
        mProjectTabAdapter.notifyDataSetChanged();
    }
}
