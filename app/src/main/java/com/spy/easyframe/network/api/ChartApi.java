package com.spy.easyframe.network.api;

import com.spy.easyframe.model.KLineModel;
import com.spy.easyframe.model.TickChartModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface ChartApi {
    @GET("/ctrade/quotation/getTickChart.do?")
    Observable<TickChartModel> getTickChart(@Query("commodityId") String commodityId,
                                            @Query("environmentCode")String environmentCode);
    @GET("/ctrade/quotation/getKLine.do?")
    Observable<KLineModel> getKLineChart(@Query("environmentCode")String environmentCode,
                                         @Query("commodityId")String commodityId,
                                         @Query("period")String period,
                                         @Query("pageSize")String pageSize,
                                         @Query("curPage")String curPage,
                                         @Query("baselinetype")String baselinetype,
                                         @Query("linetype")String linetype);
}
