package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.WeatherBean;
import com.taowei.opinions.net.DataCallback;


public abstract class WeatherCallBack extends DataCallback<WeatherBean> {
    @Override
    public WeatherBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse Weather", "Parse String  *****" + response);
       Gson gson = new Gson();
        return gson.fromJson(response,WeatherBean.class);
    }
}
