package com.taowei.opinions.net;

import com.taowei.opinions.app.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BaseRetrofit {

    private static BestService SERVICE;

    private static WeatherService weatherService;
    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10000;

    public static BestService getDefault() {

        if (SERVICE == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            /**
             * 拦截器
             */

            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl.Builder authorizedUrlBuilder = request.url()
                            .newBuilder()
                            //添加统一参数 如手机唯一标识符,token等
                            .addQueryParameter("key1", "value1")
                            .addQueryParameter("key2", "value2");
                    Request newRequest = request.newBuilder()
                            //对所有请求添加请求头
                            .header("mobileFlag", "adfsaeefe").addHeader("type", "4")
                            .method(request.method(), request.body())
                            .url(authorizedUrlBuilder.build())
                            .build();
                    return chain.proceed(newRequest);
                }
            });

            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
//                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Config.IP_URL)
                    .build().create(BestService.class);
        }

        return SERVICE;
    }


    public static WeatherService getWeather() {

        if (weatherService == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            /**
             * 拦截器
             */

            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl.Builder authorizedUrlBuilder = request.url()
                            .newBuilder()
                            //添加统一参数 如手机唯一标识符,token等
                            .addQueryParameter("key1", "value1")
                            .addQueryParameter("key2", "value2");
                    Request newRequest = request.newBuilder()
                            //对所有请求添加请求头
                            .header("mobileFlag", "adfsaeefe").addHeader("type", "4")
                            .method(request.method(), request.body())
                            .url(authorizedUrlBuilder.build())
                            .build();
                    return chain.proceed(newRequest);
                }
            });

            weatherService = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
//                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Config.WEATHER)
                    .build().create(WeatherService.class);
        }

        return weatherService;
    }
}







