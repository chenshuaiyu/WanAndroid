package com.example.chen.wanandroiddemo.main.search.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.search.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 22:15
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getHotWord() {
        addSubcriber(
                mDataManager.getHotWord()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> mView.showHotWord(listBaseResponse.getData()),
                                throwable -> throwable.printStackTrace())
        );
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
