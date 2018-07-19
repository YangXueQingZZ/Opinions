package com.taowei.opinions.base;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gyf.barlibrary.ImmersionBar;
import com.taowei.opinions.R;
import com.taowei.opinions.utils.LogUtil;
import com.taowei.opinions.utils.LunarUtils;
import com.taowei.opinions.utils.SharePreferencesHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    public LocationClient locationClient;
    //获取类名
    private final String TAG = getClass().getName();

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    //沉浸式状态栏
    private ImmersionBar immersionBar;

    private SharePreferencesHelper helper;

    protected abstract int getLayoutId();

    //初始化控件
    protected abstract void inView(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        ButterKnife.bind(this);
        inView(savedInstanceState);
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(R.color.statusBar)
                .fitsSystemWindows(true)
                .init();
        LogUtil.i(TAG, "onCreate");
        permissions();
        initLocation();
    }


    private void initLocation() {

        LocationClientOption clientOption = new LocationClientOption();
        clientOption.setScanSpan(5000);
        clientOption.setIsNeedAddress(true);
        locationClient.setLocOption(clientOption);

    }



    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            LogUtil.i(TAG, bdLocation.getCity());
            String location = bdLocation.getCity();

            SharePreferencesHelper.put(getBaseContext(), "location", location);
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                LogUtil.i(TAG, "GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                LogUtil.i(TAG, "网络");
            }

        }
    }

    private void permissions() {


        List<String> permissionsList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager
                .PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager
                .PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                .PERMISSION_GRANTED) {
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionsList.isEmpty()) {
            String[] permissions = permissionsList.toArray(new String[permissionsList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            requstLocation();
        }

    }

    private void requstLocation() {
        locationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:

                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {

                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        if (immersionBar != null) {
            immersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        }
        if (locationClient != null) {

            locationClient.stop();

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出口碑应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


}
