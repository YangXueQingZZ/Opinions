package com.taowei.opinions.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taowei.opinions.net.ActivityLifeCycleEvent;
import com.taowei.opinions.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    public Activity mActivity;
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        LogUtil.i("生命周期", "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        inData();
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void inData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
