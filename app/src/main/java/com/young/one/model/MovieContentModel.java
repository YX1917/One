package com.young.one.model;

import com.young.one.base.Callback;
import com.young.one.bean.MovieBean;
import com.young.one.bean.MovieComment;
import com.young.one.bean.MovieDetailBean;
import com.young.one.net.ApiCall;
import com.young.one.net.RetrofitManger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxing on 2018/1/10.
 */

public class MovieContentModel {
    public static void getMovieContent(String item_id, final Callback<MovieBean> callback) {
        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getMovieContent(item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieBean>() {
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
                    public void onNext(MovieBean movieBean) {
                        if (movieBean.getRes() == 0) {
                            callback.onSuccess(movieBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

    public static void getMovieComment(String item_id,String comment_id ,final Callback<MovieComment> callback) {

        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getMovieComment(item_id,comment_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieComment>() {
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
                    public void onNext(MovieComment  movieComment) {
                        if (movieComment.getRes() == 0) {
                            callback.onSuccess(movieComment);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

    public static void getMovieDetail(String item_id ,final Callback<MovieDetailBean> callback) {

        RetrofitManger.getInstance()
                .createReq(ApiCall.class)
                .getMovieDetail(item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailBean>() {
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
                    public void onNext(MovieDetailBean movieDetailBean) {
                        if (movieDetailBean.getRes() == 0) {
                            callback.onSuccess(movieDetailBean);
                        } else {
                            callback.onFailure("错误码");
                        }
                    }
                });
    }

}
