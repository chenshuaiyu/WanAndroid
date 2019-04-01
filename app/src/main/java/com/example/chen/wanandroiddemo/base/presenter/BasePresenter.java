package com.example.chen.wanandroiddemo.base.presenter;

import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 16:45
 */
public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T mView;
    protected DataManager mDataManager;
    protected CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(T v) {
        mView = v;
    }

    @Override
    public void detachView() {
        mView = null;
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    @Override
    public void addSubcriber(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribeEvent() {
//        addSubcriber(
//                RxBus.getInstance().toObservable()
//        );
    }
}
