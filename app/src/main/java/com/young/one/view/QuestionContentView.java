package com.young.one.view;

import com.young.one.base.BaseView;
import com.young.one.bean.QuestionBean;
import com.young.one.bean.QuestionCommentBean;

/**
 * Created by yangxing on 2018/1/10.
 */

public interface QuestionContentView extends BaseView {
    void showQuestionContent(QuestionBean data);
    void showQuestionRecycle(QuestionCommentBean questionCommentBean);
}
