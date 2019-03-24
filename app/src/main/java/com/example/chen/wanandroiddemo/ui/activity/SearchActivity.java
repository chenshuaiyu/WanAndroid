package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchComponent;
import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.presenter.SearchPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_button)
    Button mButton;
    @BindView(R.id.edit_text)
    EditText mEditText;

    private List<HotWord> mHotWords;
    private TagAdapter<HotWord> mTagAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject() {
        DaggerSearchComponent.builder().searchModule(new SearchModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEditText.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    searchArticles(s);
                }
            }
        });

        mHotWords = new ArrayList<>();
        mTagAdapter = new TagAdapter<HotWord>(mHotWords) {
            @Override
            public View getView(FlowLayout parent, int position, final HotWord hotWord) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                TextView textView = view.findViewById(R.id.text_view);
                textView.setText(hotWord.getName());
                view.setBackgroundColor(ColorUtil.randomTagColor());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchArticles(hotWord.getName());
                    }
                });
                return view;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter();


        presenter.getHotWord();
    }

    private void searchArticles(String key) {
        Intent intent = new Intent(SearchActivity.this, SearchArticlesActivity.class);
        intent.putExtra(Constants.SEARCH_KEY, key);
        startActivity(intent);
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

    @Override
    public void showHotWord(List<HotWord> hotWords) {
        mHotWords.addAll(hotWords);
        mTagAdapter.notifyDataChanged();
    }
}
