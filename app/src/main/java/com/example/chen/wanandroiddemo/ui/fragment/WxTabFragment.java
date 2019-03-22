package com.example.chen.wanandroiddemo.ui.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.WXTabAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.WXTabContract;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerWXTabComponent;
import com.example.chen.wanandroiddemo.di.module.WXTabModule;
import com.example.chen.wanandroiddemo.presenter.WXTabPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 18:13
 */
@SuppressLint("ValidFragment")
public class WxTabFragment extends BaseFragment<WXTabPresenter> implements WXTabContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int curPage = 0;
    private List<Article> mWXTabArticleList;
    private WXTabAdapter mWXTabAdapter;

    private Tab mWXTab;

    public WxTabFragment(Tab WXTab) {
        mWXTab = WXTab;
    }

    public Tab getWXTab() {
        return mWXTab;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_tab;
    }

    @Override
    protected void inject() {
        DaggerWXTabComponent.builder().wXTabModule(new WXTabModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mWXTabArticleList = new ArrayList<>();
        mWXTabAdapter = new WXTabAdapter(getActivity(), mWXTabArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mWXTabAdapter);

        presenter.getWXTabArticles(mWXTab.getId(), curPage++);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 0;
                presenter.getWXTabArticles(mWXTab.getId(), curPage);
                refreshLayout.finishRefresh(1500);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getWXTabArticles(mWXTab.getId() ,curPage++);
                refreshLayout.finishLoadMore(1500);
            }
        });
    }

    @Override
    public void showWXTabArticles(List<Article> wxTabArticles) {
        if (curPage == 0)
            mWXTabArticleList.clear();
        mWXTabArticleList.addAll(wxTabArticles);
        mWXTabAdapter.notifyDataSetChanged();
    }
}
