package com.example.chen.wanandroiddemo.main.todo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/27 11:37
 */
public interface ToDoContract {
    interface Presenter extends IPresenter<View> {

    }

    interface View extends BaseView {

    }
}
