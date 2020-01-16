package com.example.chen.wanandroiddemo.main.square;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SquareShareArticlesAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
import com.example.chen.wanandroiddemo.main.square.contract.MySquareContract;
import com.example.chen.wanandroiddemo.main.square.presenter.MySquarePresenter;
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

    private View mShareArticleView;
    private Button mShareBtn;

    private List<SquareShareArticles.Sharearticles.Sharearticle> mSquareArticleList = new ArrayList<>();
    private SquareShareArticlesAdapter mSquareShareArticlesAdapter;

    @Override
    protected MySquarePresenter getPresenter() {
        return new MySquarePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_my_square)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mShareArticleView = LayoutInflater.from(getContext()).inflate(R.layout.share_article, null);
        mShareBtn = mShareArticleView.findViewById(R.id.btn_share);
        mPresenter.subscribeEvent();

        mShareBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                    .setTitle(R.string.share)
                    .setView(R.layout.dialog_share_article)
                    .setCancelable(true)
                    .setPositiveButton(R.string.share, (dialog1, which) -> {
                        View view = getView();
                        assert view != null;
                        EditText titleEt = view.findViewById(R.id.et_title);
                        EditText linkEt = view.findViewById(R.id.et_link);
                        mPresenter.shareArticle(titleEt.getText().toString(), linkEt.getText().toString());
                    })
                    .show();
        });

        //缺少删除分享的文章的逻辑

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSquareShareArticlesAdapter = new SquareShareArticlesAdapter(R.layout.item_square_share_articles, mSquareArticleList);
        mRefreshRecyclerView.setAdapter(mSquareShareArticlesAdapter);
        mSquareShareArticlesAdapter.addHeaderView(mShareArticleView);

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
        mSquareShareArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            SquareShareArticles.Sharearticles.Sharearticle squareArticle = mSquareArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(getContext(), squareArticle.getId(), squareArticle.getLink(), squareArticle.getTitle(), squareArticle.isCollect());
        });
        mSquareShareArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SquareShareArticles.Sharearticles.Sharearticle squareArticle = mSquareArticleList.get(position);
            switch (view.getId()) {
                case R.id.iv_collect:

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
        mSquareShareArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showShareSuccess() {
        ToastUtil.toast(R.string.share_success);
    }

    @Override
    public void showShareFail() {
        ToastUtil.toast(R.string.share_fail);
    }

    @NonNull
    @Override
    public String toString() {
        return "我的广场";
    }
}
