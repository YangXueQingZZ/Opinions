package com.taowei.opinions.net;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BestService {

    // https://api.seniverse.com/v3/weather/now.json?key=7xxejcdmxunz2xpz&location=beijing&language=zh-Hans&unit=c


    //上传设备Id
    @GET("Userapp/check_user_info")
    Observable<String> uploadDevice(@Query("device_id") String id);
    //标签
    @POST("Goodslists/select_goods_type_by_device")
    Observable<String> getLabe(@Query("device_id") String device_id);
    //请求app二级分类
    @GET("Goodslists/select_goods_2_type_by_device")
    Observable<String> getMore(@Query("device_id") String device_id, @Query("id") int id);
    //
    //判断当前是前台还是后台
    @GET("Uidlogs/add_user_logs")
    Observable<String>addUserLog(@Query("device_id") String device_id, @Query("wid") int wid);

    //获取app列表
    @GET("Goodslists/select_app_by_goods_type2_and_device")
    Observable<String> getAppList(@Query("device_id") String device_id, @Query("goods_type2") int id);

    @GET("app_interface/app_item")
    Observable<String>getColumn(@Query("device_id") String device_id);

    @GET("app_interface/app_type_by_device")
    Observable<String>uploadLabeCheck(@Query(("device_id")) String deviceId, @Query("type_id") int id);

    @GET("app_interface/add_item_info_all")
    Observable<String>uploadPopup(@Query("device_id") String deviceId, @Query("item_id") int itemId);

}
