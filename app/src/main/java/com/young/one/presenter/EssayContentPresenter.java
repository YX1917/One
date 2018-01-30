package com.young.one.presenter;

import com.young.one.base.BasePresenter;
import com.young.one.base.Callback;
import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;
import com.young.one.model.EssayContentModel;
import com.young.one.utilcode.util.LogUtils;
import com.young.one.view.EssayContentView;

/**
 * Created by yangxing on 2018/1/10.
 */

public class EssayContentPresenter extends BasePresenter<EssayContentView> {
    public void getEssayContent(String item_id) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        EssayContentModel.getEssayContent(item_id, new Callback<EssayBean>() {
            @Override
            public void onSuccess(EssayBean data) {
                LogUtils.e(data);
                getView().showEssayContent(data);
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

    public void getEssayComment(String item_id,String comment_id) {
        if (!isViewAttached()) {
            return;
        }
        EssayContentModel.getEssayComment(item_id, comment_id,new Callback<EssayCommentBean>() {
            @Override
            public void onSuccess(EssayCommentBean data) {
                LogUtils.e(data);
                getView().showEssayRecycle(data);
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

