package com.example.chen.wanandroiddemo.main.collection.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Website;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:58
 */
public interface CollectionWebsiteContract {
    interface Presenter extends IPresenter<View> {
        void getCollectedWebsites();

        void editWebsite(int id, String name, String link, int position);

        void deleteWebsite(int id);
    }

    interface View extends BaseView {
        void showCollectedWebsites(List<Website> websites);

        void showEditResult(boolean success, String name, String link, int position);

        void showDeleteResult(boolean success);
    }
}
