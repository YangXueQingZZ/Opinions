package com.taowei.opinions.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {

    private static DownLoadManager  downLoadManager ;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public static DownLoadManager getDownLoadManager(){

        return downLoadManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        downLoadManager  = new DownLoadManager(DownloadService.this) ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放downLoadManager
        downLoadManager.stopAllTask();
        downLoadManager = null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(downLoadManager == null){
            downLoadManager = new DownLoadManager(DownloadService.this);
        }
    }


}
