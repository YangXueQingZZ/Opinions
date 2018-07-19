package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.PopupWindowBean;
import com.taowei.opinions.net.DataCallback;


public abstract class UploadPopupCallBack extends DataCallback<PopupWindowBean> {
    @Override
    public PopupWindowBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse app", "Parse String  *****" + response);
        Gson gson =  new Gson();
        return gson.fromJson(response,PopupWindowBean.class);
    }
}
