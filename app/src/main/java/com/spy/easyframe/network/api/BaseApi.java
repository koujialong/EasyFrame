package com.spy.easyframe.network.api;

import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.model.LiveListModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */
public interface BaseApi {
    @GET("/ctrade/system/getBannerList.do?")
    Observable<BannerModel> getBannerList(@Query("page") int page, @Query("limit")int limit, @Query("type")String type);

    @GET("/ctrade/live/getliveTeamlist.do?")
    Observable<LiveListModel> getliveTeamlist(@Query("environmentCode") String environmentCode, @Query("vr") double vr);
}
