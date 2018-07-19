package com.taowei.opinions.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


import com.taowei.opinions.data.AppBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.taowei.opinions.app.OpinionsAPP.getContext;


public class PackageUtil {

    public static List<Drawable> mList = new ArrayList<>();
    public static List<String> appName = new ArrayList<>();
    public static List<String> className = new ArrayList<>();
    public static List<String> packageName = new ArrayList<>();


    /*通过包名和类名打开APP*/
    public static void startAPP(String packageName, String className, Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 设置ComponentName参数1:package参数2:MainActivity路径
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        context.startActivity(intent);
    }


    public static boolean isContains(String packageName) {
        List<String> lists = getPackageName();
        for (String name : lists) {
            if (name.equals(packageName))
                return true;
        }
        return false;
    }


    //获取所有桌面应用
    public static List<String> getPackageName() {
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<String> list = new ArrayList<>();
        List<ResolveInfo> info = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo infos : info) {
            ActivityInfo ai = infos.activityInfo;

            list.add(ai.packageName);

        }
        return list;
    }

    //获取所有桌面应用
    public static void getLauncher() {
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> info = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo infos : info) {
            ActivityInfo ai = infos.activityInfo;
            Drawable icon = ai.loadIcon(packageManager);
            String name = (String) ai.loadLabel(packageManager);

            AppBean.DataBean dataBean = new AppBean.DataBean();
            dataBean.setPackge_name(ai.packageName);
            packageName.add(ai.packageName);
            className.add(ai.name);
            mList.add(icon);
            appName.add(name);

        }

    }


    public static void startApp(String name) {

        PackageManager packageManager = getContext().getPackageManager();
        Intent intent;
        intent = packageManager.getLaunchIntentForPackage(name);
        if (intent != null){
            getContext().startActivity(intent);
        }else {
            Toast.makeText(getContext(),"没找到相关应用", Toast.LENGTH_SHORT).show();
        }


    }


    public static void openFile(File var0, Context var1) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(var1, var1.getApplicationContext().getPackageName() + ".provider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, var1.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(var1, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }
    //安装app
    public static void installAPK(Context context, String path) {
        File file = new File(path);
        if (file.exists()) {
            openFile(file, context);
        } else {
            Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }

}
