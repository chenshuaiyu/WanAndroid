package com.example.chen.wanandroiddemo.main.todo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.todo.contract.ToDoContract;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/28 9:51
 */
public class ToDoPresenter extends BasePresenter<ToDoContract.View> implements ToDoContract.Presenter {

    public ToDoPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
