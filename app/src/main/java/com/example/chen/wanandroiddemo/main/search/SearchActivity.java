package com.example.chen.wanandroiddemo.main.search;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.HistoryAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.search.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.main.search.presenter.SearchPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_search)
    Button mButton;
    @BindView(R.id.et_content)
    EditText mEditText;
    @BindView(R.id.clear_all_history)
    RelativeLayout mRelativeLayout;

    private List<HotWord> mHotWords;
    private TagAdapter<HotWord> mTagAdapter;
    private List<HistoryRecord> mHistoryRecords;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected SearchPresenter getPresenter() {
        return new SearchPresenter(DataManager.getInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();

        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mButton.setOnClickListener(v -> {
            String s = mEditText.getText().toString();
            if (!TextUtils.isEmpty(s)) {
                addHisotryRecord(s);
                searchArticles(s);
            }
        });

        mHotWords = new ArrayList<>();
        mTagAdapter = new TagAdapter<HotWord>(mHotWords) {
            @Override
            public View getView(FlowLayout parent, int position, final HotWord hotWord) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                TextView textView = view.findViewById(R.id.tv_hot_word);
                textView.setText(hotWord.getName());
                view.setBackgroundColor(ColorUtil.randomTagColor());

                view.setOnClickListener(v -> {
                    setEditSelection(hotWord.getName());

                    addHisotryRecord(hotWord.getName());
                    searchArticles(hotWord.getName());
                });
                return view;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);
        mHistoryRecords = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryAdapter = new HistoryAdapter(R.layout.item_history, mHistoryRecords);
        mHistoryAdapter.setOnItemClickListener((adapter, view, position) -> {
                    String data = mHistoryRecords.get(position).getData();
                    setEditSelection(data);

                    //将此项记录从内存、数据库中删除
                    HistoryRecord record = mHistoryRecords.get(position);
                    mHistoryRecords.remove(position);
                    mHistoryAdapter.notifyDataSetChanged();
                    mPresenter.deleteHisotryRecord(record);

                    //内存、数据库添加一条记录
                    addHisotryRecord(data);

                    searchArticles(data);
                }
        );
        mHistoryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //删除内存中此项记录
            HistoryRecord record = mHistoryRecords.get(position);
            mHistoryRecords.remove(position);
            mHistoryAdapter.notifyDataSetChanged();

            //数据库删除此项记录
            mPresenter.deleteHisotryRecord(record);
        });
        mRecyclerView.setAdapter(mHistoryAdapter);


        mRelativeLayout.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(SearchActivity.this)
                    .setTitle(R.string.clear_all_history)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.clearHisotryRecord();
                            mHistoryRecords.clear();
                            mHistoryAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            dialog.show();
        });

        mPresenter.getHotWord();
        mPresenter.getAllHisotryRecord();
    }

    private void setEditSelection(String data) {
        mEditText.setText(data);
        mEditText.setSelection(data.length());
    }

    private void addHisotryRecord(String s) {
        for (int i = 0; i < mHistoryRecords.size(); i++) {
            HistoryRecord record = mHistoryRecords.get(i);
            if (record.getData().equals(s)) {
                mHistoryRecords.remove(i);
                mPresenter.deleteHisotryRecord(record);
            }
        }

        HistoryRecord record = new HistoryRecord(null, System.currentTimeMillis(), s);
        //添加至数据库
        mPresenter.addHisotryRecord(record);
        //本地显示
        mHistoryRecords.add(0, record);
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEditText.setSelection(mEditText.getText().toString().length());
        mHistoryAdapter.notifyDataSetChanged();
    }

    private void searchArticles(String key) {
        Intent intent = SearchArticlesActivity.newIntent(this, key);
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

    @Override
    public void showAllHisotryRecord(List<HistoryRecord> historyRecords) {
        mHistoryRecords.addAll(historyRecords);
        Collections.reverse(mHistoryRecords);
        mHistoryAdapter.notifyDataSetChanged();
    }
}
