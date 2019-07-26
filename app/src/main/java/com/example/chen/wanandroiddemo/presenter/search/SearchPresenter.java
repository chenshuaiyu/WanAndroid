package com.example.chen.wanandroiddemo.presenter.search;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 22:15
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getHotWord() {
        mDataManager.getHotWord()
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<HotWord>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<HotWord>> listBaseResponse) {
                        mView.showHotWord(listBaseResponse.getData());
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void addHisotryRecord(HistoryRecord record) {
        mDataManager.addHistoryRecord(record);
    }

    @Override
    public void getAllHisotryRecord() {
        List<HistoryRecord> recordList = mDataManager.getAllHistoryRecord();
        mView.showAllHisotryRecord(recordList);
    }

    @Override
    public void clearHisotryRecord() {
        mDataManager.clearHistoryRecord();
    }

    @Override
    public void deleteHisotryRecord(HistoryRecord record) {
        mDataManager.deleteHistoryRecord(record);
    }
}
