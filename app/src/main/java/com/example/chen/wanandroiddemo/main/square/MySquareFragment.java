package com.example.chen.wanandroiddemo.main.square;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.MySquareShareArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.ShareArticle;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
import com.example.chen.wanandroiddemo.main.square.contract.MySquareContract;
import com.example.chen.wanandroiddemo.main.square.presenter.MySquarePresenter;
import com.example.chen.wanandroiddemo.utils.NetUtil;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class MySquareFragment extends BaseFragment<MySquarePresenter> implements MySquareContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<ShareArticle> mSquareArticleList = new ArrayList<>();
    private MySquareShareArticlesAdapter mMySquareShareArticlesAdapter;

    @Override
    protected MySquarePresenter getPresenter() {
        return new MySquarePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_my_square)
                .setErrorLayoutResId(R.layout.not_login)
                .setErrorReLoadViewResId(R.id.tv_not_login)
                .setOnReLoadListener(() -> {
                    OpenActivityUtil.openLoginActivity(Objects.requireNonNull(getContext()));
                    Objects.requireNonNull(getActivity()).finish();
                })
                .build();
    }

    @Override
    protected void initView() {
        View shareArticleView1 = LayoutInflater.from(getContext()).inflate(R.layout.share_article, null);
        Button shareBtn = shareArticleView1.findViewById(R.id.btn_share);
        mPresenter.subscribeEvent();

        shareBtn.setOnClickListener(v -> {
            View shareArticleView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share_article, null);
            new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                    .setTitle(R.string.share)
                    .setView(shareArticleView)
                    .setCancelable(false)
                    .setPositiveButton(R.string.share, (dialog1, which) -> {
                        EditText titleEt = shareArticleView.findViewById(R.id.et_title);
                        EditText linkEt = shareArticleView.findViewById(R.id.et_link);
                        mPresenter.shareArticle(titleEt.getText().toString(), linkEt.getText().toString());
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    })
                    .show();
        });

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMySquareShareArticlesAdapter = new MySquareShareArticlesAdapter(R.layout.item_square_share_articles, mSquareArticleList);
        mRefreshRecyclerView.setAdapter(mMySquareShareArticlesAdapter);
        mMySquareShareArticlesAdapter.addHeaderView(shareArticleView1);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getMySquare(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getMySquare(page);
            }
        });
        mMySquareShareArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            ShareArticle squareArticle = mSquareArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getContext(), squareArticle.getId(), squareArticle.getLink(), squareArticle.getTitle(), squareArticle.isCollect());
        });
        mMySquareShareArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ShareArticle squareArticle = mSquareArticleList.get(position);
            switch (view.getId()) {
                case R.id.iv_collect:
                    if (!squareArticle.isCollect()) {
                        mPresenter.collectArticle(squareArticle.getId(), position);
                    } else {
                        mPresenter.cancelCollectArticle(squareArticle.getId(), position);
                    }
                    break;
                case R.id.iv_delete:
                    mPresenter.deleteShareArticle(squareArticle.getId(), position);
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void showMySquare(SquareShareArticles squareShareArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mSquareArticleList.clear();
        }
        mSquareArticleList.addAll(squareShareArticles.getShareArticles().getDatas());
        mMySquareShareArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showShareResult(boolean success) {
        if (success) {
            ToastUtil.toast(R.string.share_success);
            mRefreshRecyclerView.reLoad();
        } else {
            ToastUtil.toast(R.string.share_fail);
        }
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mSquareArticleList.get(position).setCollect(true);
            mMySquareShareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mSquareArticleList.get(position).setCollect(false);
            mMySquareShareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
    }

    @Override
    public void showDeleteResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.delete_success);
            mSquareArticleList.remove(position);
            mMySquareShareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.delete_fail);
        }
    }

    @Override
    public void reLoad() {
        if (!NetUtil.isNetworkConnected()) {
            showNetErrorView();
        } else if (mPresenter.getLoginStatus()) {
            mRefreshRecyclerView.reLoad();
        } else {
            showErrorView();
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "我的广场";
    }
}
