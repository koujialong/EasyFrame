package com.spy.easyframe.network.api;

import com.spy.easyframe.model.BannerModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */
public interface BannerApi {
    @GET("/ctrade/system/getBannerList.do?")
    Observable<BannerModel> getBanner(@Query("page") int page, @Query("limit")int limit, @Query("type")String type);
}
