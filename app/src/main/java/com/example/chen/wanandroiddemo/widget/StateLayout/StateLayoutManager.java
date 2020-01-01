package com.example.chen.wanandroiddemo.widget.StateLayout;

public class StateLayoutManager {

    private int contentLayoutResId;
    private int loadingLayoutResId;
    private int emptyDataLayoutResId;
    private int netErrorLayoutResId;
    private int errorLayoutResId;

    private int netErrorReLoadViewResId;
    private int errorReLoadViewResId;

    private OnReLoadListener onReLoadListener;

    private StateLayoutManager(Builder builder) {
        contentLayoutResId = builder.contentLayoutResId;
        loadingLayoutResId = builder.loadingLayoutResId;
        emptyDataLayoutResId = builder.emptyDataLayoutResId;
        netErrorLayoutResId = builder.netErrorLayoutResId;
        errorLayoutResId = builder.errorLayoutResId;

        netErrorReLoadViewResId = builder.netErrorReLoadViewResId;
        errorReLoadViewResId = builder.errorReLoadViewResId;

        onReLoadListener = builder.onReLoadListener;
    }

    public static final class Builder {
        private int contentLayoutResId;
        private int loadingLayoutResId;
        private int emptyDataLayoutResId;
        private int netErrorLayoutResId;
        private int errorLayoutResId;

        private int netErrorReLoadViewResId;
        private int errorReLoadViewResId;

        private OnReLoadListener onReLoadListener;

        public Builder setContentLayoutResId(int contentLayoutResId) {
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder setLoadingLayoutResId(int loadingLayoutResId) {
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder setEmptyDataLayoutResId(int emptyDataLayoutResId) {
            this.emptyDataLayoutResId = emptyDataLayoutResId;
            return this;
        }

        public Builder setNetErrorLayoutResId(int netErrorLayoutResId) {
            this.netErrorLayoutResId = netErrorLayoutResId;
            return this;
        }

        public Builder setErrorLayoutResId(int errorLayoutResId) {
            this.errorLayoutResId = errorLayoutResId;
            return this;
        }

        public Builder setNetErrorReLoadViewResId(int netErrorReLoadViewResId) {
            this.netErrorReLoadViewResId = netErrorReLoadViewResId;
            return this;
        }

        public Builder setErrorReLoadViewResId(int errorReLoadViewResId) {
            this.errorReLoadViewResId = errorReLoadViewResId;
            return this;
        }

        public Builder setOnReLoadListener(OnReLoadListener onReLoadListener) {
            this.onReLoadListener = onReLoadListener;
            return this;
        }

        public StateLayoutManager build() {
            return new StateLayoutManager(this);
        }
    }

    public int getContentLayoutResId() {
        return contentLayoutResId;
    }

    public void setContentLayoutResId(int contentLayoutResId) {
        this.contentLayoutResId = contentLayoutResId;
    }

    public int getLoadingLayoutResId() {
        return loadingLayoutResId;
    }

    public void setLoadingLayoutResId(int loadingLayoutResId) {
        this.loadingLayoutResId = loadingLayoutResId;
    }

    public int getEmptyDataLayoutResId() {
        return emptyDataLayoutResId;
    }

    public void setEmptyDataLayoutResId(int emptyDataLayoutResId) {
        this.emptyDataLayoutResId = emptyDataLayoutResId;
    }

    public int getNetErrorLayoutResId() {
        return netErrorLayoutResId;
    }

    public void setNetErrorLayoutResId(int netErrorLayoutResId) {
        this.netErrorLayoutResId = netErrorLayoutResId;
    }

    public int getErrorLayoutResId() {
        return errorLayoutResId;
    }

    public void setErrorLayoutResId(int errorLayoutResId) {
        this.errorLayoutResId = errorLayoutResId;
    }

    public int getNetErrorReLoadViewResId() {
        return netErrorReLoadViewResId;
    }

    public void setNetErrorReLoadViewResId(int netErrorReLoadViewResId) {
        this.netErrorReLoadViewResId = netErrorReLoadViewResId;
    }

    public int getErrorReLoadViewResId() {
        return errorReLoadViewResId;
    }

    public void setErrorReLoadViewResId(int errorReLoadViewResId) {
        this.errorReLoadViewResId = errorReLoadViewResId;
    }

    public OnReLoadListener getOnReLoadListener() {
        return onReLoadListener;
    }

    public void setOnReLoadListener(OnReLoadListener onReLoadListener) {
        this.onReLoadListener = onReLoadListener;
    }
}
