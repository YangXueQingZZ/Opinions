package com.taowei.opinions.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.taowei.opinions.app.OpinionsAPP.getContext;

public class DeviceUtil {

    private static final String TAG = DeviceUtil.class.getSimpleName();
    private static final String PREFS_FILE = "device_id.xml";
    private static final String PREFS_DEVICE_ID = "device_id";

    private DeviceUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断设备是否 root
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isRooted() {
        String su = "su";
        String[] locations = {
                "/system/bin/",
                "/system/xbin/",
                "/sbin/",
                "/system/sd/xbin/",
                "/system/bin/failsafe/",
                "/data/local/xbin/",
                "/data/local/bin/",
                "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备的唯一标识
     *
     * @return uuid
     */
    public static UUID getDeviceUuid() {
        UUID uuid;
        // 先从指定文件中获取事先存储的 uuid
        final SharedPreferences prefs = getContext().getSharedPreferences(PREFS_FILE, 0);
        final String id = prefs.getString(PREFS_DEVICE_ID, null);
        if (id != null) {
            uuid = UUID.fromString(id);
        } else {
            final String androidId = getAndroidID();
            // 如果 Android ID 没有问题，则使用 Android ID，
            // 如果 Android ID 有问题，则使用 DeviceID，
            // 如果 DeviceID 也不可用，则使用一个随机数 uuid 作为设备的唯一标识
            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                } else {
                    final String deviceId = getDeviceId();
                    uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"))
                            : UUID.randomUUID();
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            // 将 uuid 存储到指定文件中
            prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
        }
        return uuid;
    }

    /**
     * 获取设备 AndroidID
     * <p> 2.2（Froyo，8）版本系统会不可信，来自主要生产厂商的主流手机，至少有一个普遍发现的bug，
     * 这些有问题的手机相同的 ANDROID_ID: 9774d56d682e549c，
     * 但是如果返厂的手机，或者被 root 的手机，可能会变</p>
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备的 DeviceId（即IMEI）
     * <p>只支持有通话功能的手机，平板不支持，且有些厂家的实现有 bug，返回一些不可用的数据</p>
     *
     * @return DeviceId
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        return telephonyManager != null ? telephonyManager.getDeviceId() : null;
    }



    /**
     * 获取设备 MAC 地址
     *
     * @return MAC 地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }


}
