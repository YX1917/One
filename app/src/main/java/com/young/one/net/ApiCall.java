package com.young.one.net;

import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;
import com.young.one.bean.IdListBean;
import com.young.one.bean.MovieBean;
import com.young.one.bean.MovieComment;
import com.young.one.bean.MovieDetailBean;
import com.young.one.bean.MusicBean;
import com.young.one.bean.MusicCommentBean;
import com.young.one.bean.OneListBean;
import com.young.one.bean.QuestionBean;
import com.young.one.bean.QuestionCommentBean;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangxing on 2017/11/10.
 */

public interface ApiCall {
    /**
     * 获取id列表
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/onelist/idlist/")
    Observable<IdListBean> getIdList();

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/onelist/{id}/0")
    Observable<OneListBean> getOneList(@Path("id") String id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/essay/{content_id}")
    Observable<EssayBean> getEssayHtmlContent(@Path("content_id") String content_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/comment/praiseandtime/essay/{essay_id}/{comment_id}")
    Observable<EssayCommentBean> getEssayComment(@Path("essay_id") String essay_id,@Path("comment_id") String comment_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/question/{content_id}")
    Observable<QuestionBean> getQuestionHtmlContent(@Path("content_id") String content_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/comment/praiseandtime/question/{essay_id}/{comment_id}")
    Observable<QuestionCommentBean> getQuestionComment(@Path("essay_id") String essay_id, @Path("comment_id") String comment_id);



    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/music/detail/{content_id}")
    Observable<MusicBean> getMusicHtmlContent(@Path("content_id") String content_id);


    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/comment/praiseandtime/music/{essay_id}/{comment_id}")
    Observable<MusicCommentBean> getMusicComment(@Path("essay_id") String essay_id, @Path("comment_id") String comment_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/movie/detail/{content_id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("content_id") String content_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/movie/{content_id}/story/1/0")
    Observable<MovieBean> getMovieContent(@Path("content_id") String content_id);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("api/comment/praiseandtime/movie/{essay_id}/{comment_id}")
    Observable<MovieComment> getMovieComment(@Path("essay_id") String essay_id, @Path("comment_id") String comment_id);



}
