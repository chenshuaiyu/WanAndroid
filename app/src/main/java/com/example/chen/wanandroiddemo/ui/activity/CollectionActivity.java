package com.example.chen.wanandroiddemo.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.CollectionContract;
import com.example.chen.wanandroiddemo.di.component.DaggerCollectionComponent;
import com.example.chen.wanandroiddemo.di.module.CollectionModule;
import com.example.chen.wanandroiddemo.presenter.activity.SettingsPresenter;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity<SettingsPresenter> implements CollectionContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void inject() {
        DaggerCollectionComponent.builder().collectionModule(new CollectionModule()).build().inject(this);
    }

    @Override
    protected void initView() {
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        supportActionBar.setTitle(R.string.collection);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();
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
