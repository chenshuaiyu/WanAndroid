package com.example.chen.wanandroiddemo.main.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ProjectsAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectTabContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.project.presenter.ProjectTabPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 20:33
 */
public class ProjectTabFragment extends BaseFragment<ProjectTabPresenter> implements ProjectTabContract.View {

    private static final String BUNDLE_PROJECT_TAB = "project_tab";

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private List<Article> mArticles = new ArrayList<>();
    private ProjectsAdapter mProjectsAdapter;

    private Tab mProjectTab;

    @Override
    protected ProjectTabPresenter getPresenter() {
        return new ProjectTabPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_refresh_recycler_view)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    public static ProjectTabFragment newInstance(Tab tab) {
        Bundle args = new Bundle();
        args.putSerializable(ProjectTabFragment.BUNDLE_PROJECT_TAB, tab);
        ProjectTabFragment fragment = new ProjectTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();
        assert getArguments() != null;
        mProjectTab = (Tab) getArguments().getSerializable(BUNDLE_PROJECT_TAB);

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProjectsAdapter = new ProjectsAdapter(R.layout.item_project_tab, mArticles);
        mRefreshRecyclerView.setAdapter(mProjectsAdapter);
        mProjectsAdapter.setOnItemClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), article.getLink(), article.getTitle());
        });
        mProjectsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Article article = mArticles.get(position);
            switch (view.getId()) {
                case R.id.iv_collect:
                    if (!article.isCollect()) {
                        mPresenter.collectArticle(article.getId(), position);
                    } else {
                        mPresenter.cancelCollectArticle(article.getId(), position);
                    }
                    break;
                default:
                    break;

            }
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getProjectTabArticles(firstPage, mProjectTab.getId());
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getProjectTabArticles(page, mProjectTab.getId());
            }
        });
    }

    @Override
    public void showProjectTabArticles(List<Article> projectTabArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mArticles.clear();
        }
        mArticles.addAll(projectTabArticles);
        mProjectsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mArticles.get(position).setCollect(true);
            mProjectsAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mArticles.get(position).setCollect(false);
            mProjectsAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return mProjectTab.getName();
    }
}
