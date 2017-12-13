package com.young.one.net;

import com.young.one.bean.IdListBean;
import com.young.one.bean.OneListBean;

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
}
