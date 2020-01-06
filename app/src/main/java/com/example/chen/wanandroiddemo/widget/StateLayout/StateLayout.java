package com.example.chen.wanandroiddemo.widget.StateLayout;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

/**
 * @author chenshuaiyu
 * <p>
 * 管理不同状态页面的显示与隐藏
 * 内容页面、加载中页面、空数据页面、网络错误页面、未知错误页面
 */
public class StateLayout extends FrameLayout {

    public static final int LAYOUT_CONTENT_TYPE = 1;
    public static final int LAYOUT_LOADING_TYPE = 2;
    public static final int LAYOUT_EMPTY_DATA_TYPE = 3;
    public static final int LAYOUT_NET_ERROR_TYPE = 4;
    public static final int LAYOUT_ERROR_TYPE = 5;

    private ViewStub emptyDataVs;
    private ViewStub netErrorVs;
    private ViewStub errorVs;

    private SparseArray<View> viewSparseArray = new SparseArray<>();
    private StateLayoutManager layoutManager;

    public StateLayout(@NonNull Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showContentLayout() {
        if (viewSparseArray.get(LAYOUT_CONTENT_TYPE) != null) {
            showView(LAYOUT_CONTENT_TYPE);
        }
    }

    public void showLoadingLayout() {
        if (viewSparseArray.get(LAYOUT_LOADING_TYPE) != null) {
            showView(LAYOUT_LOADING_TYPE);
        }
    }

    public void showEmptyDataLayout() {
        if (emptyDataVs != null && viewSparseArray.get(LAYOUT_EMPTY_DATA_TYPE) == null) {
            viewSparseArray.put(LAYOUT_EMPTY_DATA_TYPE, emptyDataVs.inflate());
        }
        showView(LAYOUT_EMPTY_DATA_TYPE);
    }

    public void showNetErrorLayout() {
        if (netErrorVs != null && viewSparseArray.get(LAYOUT_NET_ERROR_TYPE) == null) {
            viewSparseArray.put(LAYOUT_NET_ERROR_TYPE, netErrorVs.inflate());
        }
        showView(LAYOUT_NET_ERROR_TYPE);
    }

    public void showErrorLayout() {
        if (errorVs != null && viewSparseArray.get(LAYOUT_ERROR_TYPE) == null) {
            viewSparseArray.put(LAYOUT_ERROR_TYPE, errorVs.inflate());
        }
        showView(LAYOUT_ERROR_TYPE);
    }

    // 显示特定layout，隐藏其它layout
    private void showView(int layoutType) {
        for (int i = 0; i < viewSparseArray.size(); i++) {
            int key = viewSparseArray.keyAt(i);
            View v = viewSparseArray.get(key);
            if (v != null) {
                if (key == layoutType) {
                    v.setVisibility(VISIBLE);
                    View reLoadView = null;
                    switch (layoutType) {
                        case LAYOUT_NET_ERROR_TYPE:
                            reLoadView = v.findViewById(layoutManager.getNetErrorReLoadViewResId());
                            break;
                        case LAYOUT_ERROR_TYPE:
                            reLoadView = v.findViewById(layoutManager.getErrorReLoadViewResId());
                            break;
                        default:
                            break;
                    }
                    if (reLoadView != null) {
                        reLoadView.setOnClickListener(v1 -> layoutManager.getOnReLoadListener().reLoad());
                    }
                } else {
                    v.setVisibility(GONE);
                }
            }
        }
    }

    public void setLayoutManager(StateLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        if (this.layoutManager.getContentLayoutResId() != 0) {
            View view = getView(this.layoutManager.getContentLayoutResId());
            viewSparseArray.append(LAYOUT_CONTENT_TYPE, view);
            addView(view);
        }
        if (this.layoutManager.getLoadingLayoutResId() != 0) {
            View view = getView(this.layoutManager.getLoadingLayoutResId());
            viewSparseArray.append(LAYOUT_LOADING_TYPE, view);
            addView(view);
        }
        if (this.layoutManager.getEmptyDataLayoutResId() != 0) {
            this.emptyDataVs = getViewStub(this.layoutManager.getEmptyDataLayoutResId());
            addView(this.emptyDataVs);
        }
        if (this.layoutManager.getNetErrorLayoutResId() != 0) {
            this.netErrorVs = getViewStub(this.layoutManager.getNetErrorLayoutResId());
            addView(this.netErrorVs);
        }
        if (this.layoutManager.getErrorLayoutResId() != 0) {
            this.errorVs = getViewStub(this.layoutManager.getErrorLayoutResId());
            addView(this.errorVs);
        }
    }

    public void reLoad() {
        if (layoutManager.getOnReLoadListener() != null) {
            layoutManager.getOnReLoadListener().reLoad();
        }
    }

    private View getView(@LayoutRes int layoutResId) {
        return inflate(getContext(), layoutResId, null);
    }

    private ViewStub getViewStub(int layoutResId) {
        ViewStub viewStub = new ViewStub(getContext());
        viewStub.setLayoutResource(layoutResId);
        return viewStub;
    }
}
