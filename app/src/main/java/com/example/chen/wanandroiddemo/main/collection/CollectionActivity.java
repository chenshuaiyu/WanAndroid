package com.example.chen.wanandroiddemo.main.collection;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.presenter.SettingsPresenter;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionContract;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity<SettingsPresenter> implements CollectionContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected SettingsPresenter getPresenter() {
        return new SettingsPresenter(DataManager.getInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.my_collection);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
