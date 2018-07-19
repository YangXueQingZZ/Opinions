package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.LabeBean;
import com.taowei.opinions.net.DataCallback;


public abstract class LabeCallBack extends DataCallback<LabeBean> {
    @Override
    public LabeBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse labe","Parse String  *****"+response);
        Gson gson = new Gson();
        return gson.fromJson(response, LabeBean.class);
    }
}
