package com.example.chen.wanandroiddemo.ui.search;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.di.component.DaggerSearchComponent;
import com.example.chen.wanandroiddemo.di.module.SearchModule;
import com.example.chen.wanandroiddemo.presenter.search.SearchPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtils;
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
    @BindView(R.id.search_button)
    Button mButton;
    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.clear_all_history)
    RelativeLayout mRelativeLayout;

    private List<HotWord> mHotWords;
    private TagAdapter<HotWord> mTagAdapter;
    private List<HistoryRecord> mHistoryRecords;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject() {
        DaggerSearchComponent.builder().searchModule(new SearchModule()).build().inject(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
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
                TextView textView = view.findViewById(R.id.text_view);
                textView.setText(hotWord.getName());
                view.setBackgroundColor(ColorUtils.randomTagColor());

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
                    presenter.deleteHisotryRecord(record);

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
            presenter.deleteHisotryRecord(record);
        });
        mRecyclerView.setAdapter(mHistoryAdapter);


        mRelativeLayout.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(SearchActivity.this)
                    .setTitle(R.string.clear_all_history)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.clearHisotryRecord();
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

        presenter.getHotWord();
        presenter.getAllHisotryRecord();
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
                presenter.deleteHisotryRecord(record);
            }
        }

        HistoryRecord record = new HistoryRecord(null, System.currentTimeMillis(), s);
        //添加至数据库
        presenter.addHisotryRecord(record);
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
