package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.LabeCheckBean;
import com.taowei.opinions.net.DataCallback;


public abstract class LabeCheckCallBack extends DataCallback<LabeCheckBean> {


    @Override
    public LabeCheckBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse LabeCheck", "Parse String  *****" + response);
        Gson gson = new Gson();
        return gson.fromJson(response,LabeCheckBean.class);
    }
}
