package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.MoreBean;
import com.taowei.opinions.net.DataCallback;


public abstract class MoreCallBack extends DataCallback<MoreBean> {


    @Override
    public MoreBean parseNetworkResponse(String response) throws Exception {

        Log.e("Parse more", "Parse String  *****" + response);
        Gson gson = new Gson();
        return gson.fromJson(response, MoreBean.class);
    }
}
