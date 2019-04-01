package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.NavigationContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.utils.RxUtils;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 15:20
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {
    @Inject
    public NavigationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getNavigationTab() {
        mDataManager.getNavigation()
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<Navigation>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<Navigation>> listBaseResponse) {
                        mView.showNavigationTab(listBaseResponse.getData());
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
