package com.taowei.opinions.app;

import android.app.Application;
import android.content.Context;


public class OpinionsAPP extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }
}
