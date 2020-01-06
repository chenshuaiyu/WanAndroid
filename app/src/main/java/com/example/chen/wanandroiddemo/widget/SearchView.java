package com.example.chen.wanandroiddemo.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.chen.wanandroiddemo.R;

public class SearchView extends LinearLayout {
    private ClickCallback callback;
    private EditText mContentEt;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.common_search_view, this);
        mContentEt = findViewById(R.id.et_content);
        Button mSearchBtn = findViewById(R.id.btn_search);
        mSearchBtn.setOnClickListener(v ->
                callback.onSearchClick(mContentEt.getText().toString())
        );
    }

    public void clear() {
        mContentEt.setText("");
    }

    public interface ClickCallback {
        void onSearchClick(String content);
    }

    public void setCallback(ClickCallback callback) {
        this.callback = callback;
    }
}
