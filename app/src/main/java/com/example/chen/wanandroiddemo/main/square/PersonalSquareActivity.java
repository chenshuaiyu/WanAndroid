package com.example.chen.wanandroiddemo.main.square;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SquareShareArticlesAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseLoadActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.ShareArticle;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;
import com.example.chen.wanandroiddemo.main.square.contract.PersonalSquareContract;
import com.example.chen.wanandroiddemo.main.square.presenter.PersonalSquarePresenter;
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
public class PersonalSquareActivity extends BaseLoadActivity<PersonalSquarePresenter> implements PersonalSquareContract.View {

    public static final String INTENT_KEY_SHARE_USER_NAME = "share_user_name";
    public static final String INTENT_KEY_SHARE_USER_ID = "share_user_id";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private View mCoinInfoView;

    private String mShareUserName;
    private int mShareUserId;

    private List<ShareArticle> mSquareArticleList = new ArrayList<>();
    private SquareShareArticlesAdapter mSquareShareArticlesAdapter;

    @Override
    protected PersonalSquarePresenter getPresenter() {
        return new PersonalSquarePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.activity_personal_square)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mCoinInfoView = LayoutInflater.from(this).inflate(R.layout.my_coin, null);
        Intent intent = getIntent();
        mShareUserName = intent.getStringExtra(INTENT_KEY_SHARE_USER_NAME);
        mShareUserId = intent.getIntExtra(INTENT_KEY_SHARE_USER_ID, 0);
        mPresenter.subscribeEvent();
        initToolbar();

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSquareShareArticlesAdapter = new SquareShareArticlesAdapter(R.layout.item_square_share_articles, mSquareArticleList);
        mRefreshRecyclerView.setAdapter(mSquareShareArticlesAdapter);
        mSquareShareArticlesAdapter.addHeaderView(mCoinInfoView);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getPersonalSquare(mShareUserId, firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getPersonalSquare(mShareUserId, page);
            }
        });
        mSquareShareArticlesAdapter.setOnItemClickListener((adapter, view, position) -> {
            ShareArticle squareArticle = mSquareArticleList.get(position);
            OpenActivityUtil.openArticleDetailActivity(this, squareArticle.getId(), squareArticle.getLink(), squareArticle.getTitle(), squareArticle.isCollect());
        });
        mSquareShareArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ShareArticle squareArticle = mSquareArticleList.get(position);
            switch (view.getId()) {
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

    private void initToolbar() {
        mToolbar.setTitle(mShareUserName);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public void showCoinInfo(Coin coininfo) {
        TextView mCoinCountTv = mCoinInfoView.findViewById(R.id.tv_coin_count);
        TextView mRankTv = mCoinInfoView.findViewById(R.id.tv_rank);
        TextView mLvTv = mCoinInfoView.findViewById(R.id.tv_lv);
        mCoinCountTv.setText(String.valueOf(coininfo.getCoinCount()));
        mRankTv.setText(String.valueOf(coininfo.getRank()));
        mLvTv.setText(String.valueOf(coininfo.getLevel()));
    }

    @Override
    public void showPersonalSquare(SquareShareArticles squareShareArticles) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mSquareArticleList.clear();
        }
        mSquareArticleList.addAll(squareShareArticles.getShareArticles().getDatas());
        mSquareShareArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.collect_success);
            mSquareArticleList.get(position).setCollect(true);
            mSquareShareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.collect_fail);
        }
    }

    @Override
    public void showCancelCollectResult(boolean success, int position) {
        if (success) {
            ToastUtil.toast(R.string.cancel_collect_success);
            mSquareArticleList.get(position).setCollect(false);
            mSquareShareArticlesAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.cancel_collect_fail);
        }
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
}
