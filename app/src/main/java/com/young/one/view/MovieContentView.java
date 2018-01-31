package com.young.one.view;

import com.young.one.base.BaseView;
import com.young.one.bean.MovieBean;
import com.young.one.bean.MovieComment;
import com.young.one.bean.MovieDetailBean;

/**
 * Created by yangxing on 2018/1/10.
 */

public interface MovieContentView extends BaseView {
    void showMovieContent(MovieBean data);
    void showMovieRecycle(MovieComment movieComment);
    void showMovieDetail(MovieDetailBean detailBean);
}
