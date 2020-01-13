package com.example.chen.wanandroiddemo.main.square;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SquareArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.SquareArticles;
import com.example.chen.wanandroiddemo.main.square.contract.SquareListContract;
import com.example.chen.wanandroiddemo.main.square.presenter.SquareListPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SquareListFragment extends BaseFragment<SquareListPresenter> implements SquareListContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<SquareArticles.SquareArticle> mSquareArticleList = new ArrayList<>();
    private SquareArticlesAdapter mSquareArticlesAdapter;

    @Override
    protected SquareListPresenter getPresenter() {
        return new SquareListPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_square_list)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRefreshRecyclerView.setFirstPage(0);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSquareArticlesAdapter = new SquareArticlesAdapter(R.layout.item_square_list, mSquareArticleList);
        mRefreshRecyclerView.setAdapter(mSquareArticlesAdapter);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getSquareList(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getSquareList(page);
            }
        });
        mSquareArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            SquareArticles.SquareArticle squareArticle = mSquareArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), squareArticle.getId(), squareArticle.getLink(), squareArticle.getTitle());
        });
        mSquareArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SquareArticles.SquareArticle squareArticle = mSquareArticleList.get(position);
            switch (view.getId()) {
                case R.id.tv_author:

                    break;
                case R.id.iv_collect:
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void showSquareList(SquareArticles squareArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mSquareArticleList.clear();
        }
        mSquareArticleList.addAll(squareArticles.getDatas());
        mSquareArticlesAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "广场";
    }
}
