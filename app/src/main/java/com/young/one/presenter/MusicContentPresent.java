package com.young.one.presenter;

import com.young.one.base.BasePresenter;
import com.young.one.base.Callback;
import com.young.one.bean.MusicBean;
import com.young.one.bean.MusicCommentBean;
import com.young.one.model.MusicContentModel;
import com.young.one.view.MusicView;

/**
 * Created by yangxing on 2018/1/31.
 */

public class MusicContentPresent extends BasePresenter<MusicView> {
    public void getMusicContent(String contentId) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        MusicContentModel.getMusicContent(contentId, new Callback<MusicBean>() {
            @Override
            public void onSuccess(MusicBean data) {
                getView().showContent(data);
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

    public void getMusicComment(String item_id,String comment_id) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        MusicContentModel.getMusicComment(item_id,comment_id, new Callback<MusicCommentBean>() {
            @Override
            public void onSuccess(MusicCommentBean data) {
                getView().showComment(data);
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
