package com.young.one.presenter;

import com.young.one.base.BasePresenter;
import com.young.one.base.Callback;
import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;
import com.young.one.model.OneListModel;
import com.young.one.view.OneListView;

/**
 * Created by yangxing on 2017/11/10.
 */

public class OneListPresenter extends BasePresenter<OneListView> {
    /**
     * 获取网络数据
     */
    public void getIdList() {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        OneListModel.getIdList(new Callback<IdListBean>() {
            @Override
            public void onSuccess(IdListBean idListBean) {
                getView().initRecycleDate(idListBean);
            }

            @Override
            public void onFailure(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onError(Throwable e) {
                getView().showErr(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getOneList(String oneId) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        OneListModel.getOneList(oneId, new Callback<OneListBean>() {
            @Override
            public void onSuccess(OneListBean oneListBean) {
                getView().showData(oneListBean);
            }

            @Override
            public void onFailure(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onError(Throwable e) {
                getView().showErr(e);
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }
}
