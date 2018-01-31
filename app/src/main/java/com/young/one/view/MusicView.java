package com.young.one.view;

import com.young.one.base.BaseView;
import com.young.one.bean.MusicBean;
import com.young.one.bean.MusicCommentBean;

/**
 * Created by yangxing on 2018/1/31.
 */

public interface MusicView extends BaseView {
    void showContent(MusicBean musicBean);
    void showComment(MusicCommentBean musicBean);
}
