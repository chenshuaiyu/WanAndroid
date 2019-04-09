package com.example.chen.wanandroiddemo.base.activity;

import android.view.View;
import android.view.ViewGroup;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import static com.example.chen.wanandroiddemo.app.Constants.ERROR_VIEW_STATE;
import static com.example.chen.wanandroiddemo.app.Constants.LOADING_VIEW_STATE;
import static com.example.chen.wanandroiddemo.app.Constants.NORMAL_VIEW_STATE;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/9 20:32
 */
public abstract class BaseLoadActivity<T extends IPresenter> extends BaseActivity<T> {
    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;
//    private ImageView mIvReload;
    private int mCurrentState = Constants.NORMAL_VIEW_STATE;

    @Override
    protected void initView() {
        mNormalView = findViewById(R.id.refresh_layout);
        if (mNormalView == null || !(mNormalView.getParent() instanceof ViewGroup))
            throw new IllegalStateException("mNormalView error.");

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(this, R.layout.error_view, parent);
        View.inflate(this, R.layout.loading_view, parent);
        mErrorView = parent.findViewById(R.id.error);
        mLoadingView = parent.findViewById(R.id.loading);

//        mLoadingView.setVisibility(View.INVISIBLE);
//        mNormalView.setVisibility(View.VISIBLE);
//        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorView() {
        if (mCurrentState != ERROR_VIEW_STATE){
            hideCurrentView();
            mCurrentState = ERROR_VIEW_STATE;
            showCurrentView();
        }
    }

    @Override
    public void showLoadingView() {
        if (mCurrentState != LOADING_VIEW_STATE){
            hideCurrentView();
            mCurrentState = LOADING_VIEW_STATE;
            showCurrentView();
        }
    }

    @Override
    public void showNormalView() {
        if (mCurrentState != NORMAL_VIEW_STATE){
            hideCurrentView();
            mCurrentState = NORMAL_VIEW_STATE;
            showCurrentView();
        }
    }

    private void showCurrentView() {
        View showView = null;
        switch (mCurrentState) {
            case Constants.NORMAL_VIEW_STATE:
                showView = mNormalView;
                break;
            case Constants.LOADING_VIEW_STATE:
                showView = mLoadingView;
                break;
            case ERROR_VIEW_STATE:
                showView = mErrorView;
                break;
            default:
                break;
        }
        if (showView == null) return;
        showView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        View hideView = null;
        switch (mCurrentState) {
            case Constants.NORMAL_VIEW_STATE:
                hideView = mNormalView;
                break;
            case Constants.LOADING_VIEW_STATE:
                hideView = mLoadingView;
                break;
            case ERROR_VIEW_STATE:
                hideView = mErrorView;
                break;
            default:
                break;
        }
        if (hideView == null) return;
        hideView.setVisibility(View.INVISIBLE);
    }
}
