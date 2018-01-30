package com.young.one.model;

import com.young.one.base.Callback;
import com.young.one.bean.QuestionBean;
import com.young.one.bean.QuestionCommentBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxing on 2018/1/10.
 */

public class QuestionContentModel {
    public static void getContent(String item_id, final Callback<QuestionBean> callback) {
        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getQuestionHtmlContent(item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuestionBean>() {
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
                    public void onNext(QuestionBean questionBean) {
                        if (questionBean.getRes() == 0) {
                            callback.onSuccess(questionBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

    public static void getComment(String item_id, String comment_id, final Callback<QuestionCommentBean> callback) {
        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getQuestionComment(item_id, comment_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuestionCommentBean>() {
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
                    public void onNext(QuestionCommentBean questionCommentBean) {
                        if (questionCommentBean.getRes() == 0) {
                            callback.onSuccess(questionCommentBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

}
