package com.spy.easyframe.network.api;

import com.spy.easyframe.model.LiveListModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/24.
 */

public interface LiveListApi {
    @GET("/ctrade/live/getliveTeamlist.do?")
    Observable<LiveListModel> getLiveList(@Query("environmentCode") String environmentCode, @Query("vr") int vr);
}
