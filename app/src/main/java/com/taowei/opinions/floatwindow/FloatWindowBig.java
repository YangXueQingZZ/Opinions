package com.taowei.opinions.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taowei.opinions.R;
import com.taowei.opinions.service.FloatingWindowService;
import com.taowei.opinions.ui.activity.MainActivity;

/**
 * Author:pdm on 2016/10/12
 * Email:aiyh0202@163.com
 */
public class FloatWindowBig extends LinearLayout {
    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    public FloatWindowBig(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        TextView close = (TextView) findViewById(R.id.close);
        TextView back = (TextView) findViewById(R.id.back);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				// 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
//				FloatWindowManager.removeBigWindow(context);
//				FloatWindowManager.removeSmallWindow(context);
//				Intent intent = new Intent(getContext(), FloatingWindowService.class);
//				context.stopService(intent);
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                FloatWindowManager.removeBigWindow(context);
                FloatWindowManager.createSmallWindow(context);

            }
        });
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
                FloatWindowManager.removeBigWindow(context);
                FloatWindowManager.createSmallWindow(context);
            }
        });
    }
}
