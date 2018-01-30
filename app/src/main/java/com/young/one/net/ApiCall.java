package com.young.one.net;

import com.young.one.bean.EssayBean;
import com.young.one.bean.EssayCommentBean;
import com.young.one.bean.IdListBean;
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
    @GET("api/comment/praiseandtime/essay/{essay_id}/{comment_id}")
    Observable<QuestionCommentBean> getQuestionComment(@Path("essay_id") String essay_id, @Path("comment_id") String comment_id);

}
