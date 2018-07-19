package com.taowei.opinions.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taowei.opinions.R;
import com.taowei.opinions.base.BaseFragment;

import static com.taowei.opinions.app.Config.ARG;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {


    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance(String args) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG, args);
        VideoFragment videoFragment = new VideoFragment();

        videoFragment.setArguments(bundle);
        return videoFragment;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void inData() {

    }

}
