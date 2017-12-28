package com.young.one.model;

import com.young.one.base.Callback;
import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;
import com.young.one.utilcode.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxing on 2017/11/10.
 */

public class OneListModel {
    public static void getIdList(final Callback<IdListBean> callback) {

        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getIdList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<IdListBean>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(IdListBean idListBean) {
                        switch (idListBean.getRes()) {
                            case 0:
                                callback.onSuccess(idListBean);
                                break;
                            default:
                                callback.onFailure(idListBean.getRes() + "");
                                break;
                        }

                    }
                });
    }

    public static void getOneList(String oneListId,final Callback<OneListBean> callback) {

        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getOneList(oneListId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<OneListBean>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.toString());
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(OneListBean oneListBean) {
                        switch (oneListBean.getRes()) {
                            case 0:
                                callback.onSuccess(oneListBean);
                                break;
                            default:
                                callback.onFailure(oneListBean.getRes() + "");
                                break;
                        }

                    }
                });
    }
}
