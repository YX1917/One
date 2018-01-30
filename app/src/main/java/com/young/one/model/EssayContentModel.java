package com.young.one.model;

import com.young.one.base.Callback;
import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxing on 2018/1/10.
 */

public class EssayContentModel {
    public static void getEssayContent(String item_id, final Callback<EssayBean> callback) {
        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getEssayHtmlContent(item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EssayBean>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(EssayBean essayBean) {
                        if (essayBean.getRes() == 0) {
                            callback.onSuccess(essayBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

    public static void getEssayComment(String item_id,String comment_id ,final Callback<EssayCommentBean> callback) {
        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getEssayComment(item_id,comment_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EssayCommentBean>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(EssayCommentBean  essayCommentBean) {
                        if (essayCommentBean.getRes() == 0) {
                            callback.onSuccess(essayCommentBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

}
