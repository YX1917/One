package com.young.one.presenter;

import com.young.one.base.BasePresenter;
import com.young.one.base.Callback;
import com.young.one.bean.QuestionBean;
import com.young.one.bean.QuestionCommentBean;
import com.young.one.model.QuestionContentModel;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.view.QuestionContentView;

/**
 * Created by yangxing on 2018/1/10.
 */

public class QuestionContentPresenter extends BasePresenter<QuestionContentView> {
    public void getQuestionContent(String item_id) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        QuestionContentModel.getContent(item_id, new Callback<QuestionBean>() {
            @Override
            public void onSuccess(QuestionBean data) {
                LogUtils.e(data);
                getView().showQuestionContent(data);
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

    public void getQuestionComment(String item_id,String comment_id) {
        if (!isViewAttached()) {
            return;
        }
        QuestionContentModel.getComment(item_id, comment_id,new Callback<QuestionCommentBean>() {
            @Override
            public void onSuccess(QuestionCommentBean data) {
                LogUtils.e(data);
                getView().showQuestionRecycle(data);
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

