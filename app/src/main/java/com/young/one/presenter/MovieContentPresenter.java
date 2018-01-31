package com.young.one.presenter;

import com.young.one.base.BasePresenter;
import com.young.one.base.Callback;
import com.young.one.bean.MovieBean;
import com.young.one.bean.MovieComment;
import com.young.one.bean.MovieDetailBean;
import com.young.one.model.MovieContentModel;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.view.MovieContentView;

/**
 * Created by yangxing on 2018/1/10.
 */

public class MovieContentPresenter extends BasePresenter<MovieContentView> {
    public void getMovieContent(String item_id) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        MovieContentModel.getMovieContent(item_id, new Callback<MovieBean>() {
            @Override
            public void onSuccess(MovieBean data) {
                LogUtils.e(data);
                getView().showMovieContent(data);
            }

            @Override
            public void onFailure(String msg) {
                getView().hideLoading();
                getView().showToast(msg);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                getView().showToast(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void getMovieComment(String item_id,String comment_id) {
        if (!isViewAttached()) {
            return;
        }
        MovieContentModel.getMovieComment(item_id, comment_id,new Callback<MovieComment>() {
            @Override
            public void onSuccess(MovieComment data) {
                LogUtils.e(data);
                getView().showMovieRecycle(data);
            }

            @Override
            public void onFailure(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onError(Throwable e) {
                getView().showToast(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void getMovieDetail(String item_id) {
        if (!isViewAttached()) {
            return;
        }
        MovieContentModel.getMovieDetail(item_id,new Callback<MovieDetailBean>() {
            @Override
            public void onSuccess(MovieDetailBean data) {
                LogUtils.e(data);
                getView().showMovieDetail(data);
            }

            @Override
            public void onFailure(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onError(Throwable e) {
                getView().showToast(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }
}

