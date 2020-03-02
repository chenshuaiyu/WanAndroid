package com.example.chen.wanandroiddemo.main.search.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.HotWord;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 22:14
 */
public interface SearchContract {
    interface Presenter extends IPresenter<View> {
        void getHotWord();
        void addHisotryRecord(HistoryRecord record);
        void getAllHisotryRecord();
        void clearHisotryRecord();
        void deleteHisotryRecord(HistoryRecord record);
        void associate(String key);
    }

    interface View extends BaseView {
        void showHotWord(List<HotWord> hotWords);
        void showAllHisotryRecord(List<HistoryRecord> historyRecords);
        void showAssociation(List<String> words);
    }
}
