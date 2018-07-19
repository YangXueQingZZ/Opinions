package com.taowei.opinions.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {


    @GET("now.json?key=7xxejcdmxunz2xpz")
    Observable<String> getWeather(@Query("location") String CITY);
}
