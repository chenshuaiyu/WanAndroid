package com.example.chen.wanandroiddemo.main.square;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SquareArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.ShareArticle;
import com.example.chen.wanandroiddemo.main.square.contract.SquareListContract;
import com.example.chen.wanandroiddemo.main.square.presenter.SquareListPresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class SquareListFragment extends BaseFragment<SquareListPresenter> implements SquareListContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<ShareArticle> mSquareArticleList = new ArrayList<>();
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
            ShareArticle squareArticle = mSquareArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getActivity(), squareArticle.getLink(), squareArticle.getTitle());
        });
        mSquareArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ShareArticle squareArticle = mSquareArticleList.get(position);
            switch (view.getId()) {
                case R.id.tv_author:
                    startActivity(PersonalSquareActivity.newIntent(getContext(), squareArticle.getShareUser(), squareArticle.getUserId()));
                    break;
                case R.id.iv_collect:
                    if (!squareArticle.isCollect()) {
                        mPresenter.collectArticle(squareArticle.getId(), position);
                    } else {
                        mPresenter.cancelCollectArticle(squareArticle.getId(), position);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void showSquareList(List<ShareArticle> squareArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mSquareArticleList.clear();
        }
        mSquareArticleList.addAll(squareArticles);
        mSquareArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mSquareArticleList.get(position).setCollect(true);
            mSquareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mSquareArticleList.get(position).setCollect(false);
            mSquareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return getResources().getString(R.string.square);
    }
}
