package com.taowei.opinions.net.callback;

import android.util.Log;

import com.google.gson.Gson;
import com.taowei.opinions.data.LoginBean;
import com.taowei.opinions.net.DataCallback;


public abstract class UploadDeviceCallBack extends DataCallback<LoginBean> {


    @Override
    public LoginBean parseNetworkResponse(String response) throws Exception {
        Log.e("Parse labe", "Parse String  *****" + response);
        Gson gson = new Gson();
        return gson.fromJson(response, LoginBean.class);
    }

}
