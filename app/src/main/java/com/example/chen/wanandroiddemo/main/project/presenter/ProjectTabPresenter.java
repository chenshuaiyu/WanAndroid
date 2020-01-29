package com.example.chen.wanandroiddemo.main.project.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectTabContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:48
 */
public class ProjectTabPresenter extends BasePresenter<ProjectTabContract.View> implements ProjectTabContract.Presenter {

    public ProjectTabPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProjectTabArticles(int page, int cid) {
        addSubcriber(
                mDataManager.getProjectTabArticles(page, cid)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showProjectTabArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void collectArticle(int id, int position) {
        addSubcriber(
                mDataManager.collectArticle(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE, position)
                                , Throwable::printStackTrace)
        );
    }

    @Override
    public void cancelCollectArticle(int id, int position) {
        addSubcriber(
                mDataManager.cancelCollect(id)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(baseResponse -> mView.showCancelCollectResult(baseResponse.getErrorCode() == Constants.SUCCESS_CODE, position)
                                , Throwable::printStackTrace)
        );
    }
}
