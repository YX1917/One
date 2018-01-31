package com.young.one.model;

import com.young.one.base.Callback;
import com.young.one.bean.MusicBean;
import com.young.one.bean.MusicCommentBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxing on 2018/1/31.
 */

public class MusicContentModel {
    public static void getMusicContent(String contentId, final Callback<MusicBean> callback) {
        RetrofitManger.getInstance().createReq(ApiCall.class)
                .getMusicHtmlContent(contentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MusicBean>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                        callback.onError(e);
                    }

                    @Override
                    public void onNext(MusicBean musicBean) {
                        if (musicBean.getRes() == 0) {
                            callback.onSuccess(musicBean);
                        } else {
                            callback.onFailure(String.valueOf(musicBean.getRes()));
                        }
                    }
                });

    }

    public static void getMusicComment(String item_id,String comment_id, final Callback<MusicCommentBean> callback) {
        RetrofitManger.getInstance().createReq(ApiCall.class)
                .getMusicComment(item_id,comment_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MusicCommentBean>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                        callback.onError(e);
                    }

                    @Override
                    public void onNext(MusicCommentBean musicCommentBean) {
                        if (musicCommentBean.getRes() == 0) {
                            callback.onSuccess(musicCommentBean);
                        } else {
                            callback.onFailure(String.valueOf(musicCommentBean.getRes()));
                        }
                    }
                });

    }
}
