package com.young.one.view;

import com.young.one.base.BaseView;
import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;

/**
 * Created by yangxing on 2018/1/10.
 */

public interface EssayContentView extends BaseView {
    void showEssayContent(EssayBean data);
    void showEssayRecycle(EssayCommentBean essayCommentBean);
}
