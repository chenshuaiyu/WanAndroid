package com.example.chen.wanandroiddemo.main.search;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.HistoryAdapter;
import com.example.chen.wanandroiddemo.adapter.SearchAssociationAdapter;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.main.search.contract.SearchContract;
import com.example.chen.wanandroiddemo.main.search.presenter.SearchPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author chenshuaiyu
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_search)
    Button mSearchBtn;
    @BindView(R.id.et_content)
    EditText mContentEt;
    @BindView(R.id.rl_clear_all_history)
    RelativeLayout mClearAllHistoryRl;

    private List<HotWord> mHotWords = new ArrayList<>();
    private List<HistoryRecord> mHistoryRecords = new ArrayList<>();
    private TagAdapter<HotWord> mTagAdapter;
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

        initToolbar();

        mSearchBtn.setOnClickListener(v -> {
            String content = mContentEt.getText().toString();
            if (!TextUtils.isEmpty(content)) {
                addHisotryRecord(content);
                searchArticles(content);
            }
        });

        initTagFlowLayout();
        initRecyclerView();

        mClearAllHistoryRl.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(SearchActivity.this)
                    .setTitle(R.string.clear_all_history)
                    .setPositiveButton(R.string.confirm, (dialog1, which) -> {
                        mPresenter.clearHisotryRecord();
                        mHistoryRecords.clear();
                        mHistoryAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.cancel, (dialog12, which) -> {
                    })
                    .create();
            dialog.show();
        });
        mPresenter.getHotWord();
        mPresenter.getAllHisotryRecord();

        //搜索联想
        mPresenter.addSubcriber(
                RxTextView.textChanges(mContentEt)
                        .debounce(1, TimeUnit.SECONDS).skip(1)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(charSequence -> {
                            mPresenter.associate(charSequence.toString());
                        }, Throwable::printStackTrace)
        );
    }

    private void initRecyclerView() {
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
    }

    private void initTagFlowLayout() {
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
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void setEditSelection(String data) {
        mContentEt.setText(data);
        mContentEt.setSelection(data.length());
    }

    private void addHisotryRecord(String s) {
        for (int i = 0; i < mHistoryRecords.size(); i++) {
            HistoryRecord record = mHistoryRecords.get(i);
            if (record.getData().equals(s)) {
                mHistoryRecords.remove(i);
                mPresenter.deleteHisotryRecord(record);
                break;
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
        mContentEt.setSelection(mContentEt.getText().toString().length());
        mHistoryAdapter.notifyDataSetChanged();
    }

    private void searchArticles(String key) {
        Intent intent = SearchArticlesActivity.newIntent(this, key);
        startActivity(intent);
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

    @Override
    public void showAssociation(List<String> words) {
        initPopupWindow(words);
    }

    private void initPopupWindow(List<String> words) {
        View view = getLayoutInflater().inflate(R.layout.search_association, null);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchAssociationAdapter searchAssociationAdapter = new SearchAssociationAdapter(R.layout.item_search_association, words);
        recyclerView.setAdapter(searchAssociationAdapter);

        PopupWindow popupWindow = new PopupWindow(view, mContentEt.getWidth(), 200);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        popupWindow.showAsDropDown(mContentEt);

        searchAssociationAdapter.setOnItemClickListener((adapter, view1, position) -> {
            String s = words.get(position);
            mContentEt.setText(s);
            mContentEt.setSelection(s.length());
            popupWindow.dismiss();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
