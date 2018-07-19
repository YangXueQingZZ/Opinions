package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.ColumnBean;
import com.taowei.opinions.net.DataCallback;


public abstract class ColumnCallBack extends DataCallback<ColumnBean> {
    @Override
    public ColumnBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse app", "Parse String  *****" + response);
        Gson gson = new Gson();
        return gson.fromJson(response,ColumnBean.class);
    }
}
