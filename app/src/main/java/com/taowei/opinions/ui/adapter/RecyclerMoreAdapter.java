package com.taowei.opinions.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taowei.opinions.R;
import com.taowei.opinions.app.Config;
import com.taowei.opinions.data.MoreBean;
import com.taowei.opinions.utils.PackageUtil;


import java.util.ArrayList;
import java.util.List;

public class RecyclerMoreAdapter extends BaseQuickAdapter<MoreBean.DataBean, BaseViewHolder> {


    private List<String> pakegeNames = new ArrayList<>();
    private List<FrameLayout> rls = new ArrayList<>();

    public RecyclerMoreAdapter(int layoutResId, @Nullable List<MoreBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreBean.DataBean item) {

        helper.setText(R.id.tv_more_name_one, item.getType_name())
                .setText(R.id.tv_more_name, item.getApp_name())
                .setText(R.id.tv_more_details, "详情")
                .setText(R.id.tv_mores, "更多")
                .addOnClickListener(R.id.tv_mores)
                .addOnClickListener(R.id.tv_more_details)
                .addOnClickListener(R.id.iv_more_icon);

        Glide.with(mContext).load(Config.RESOURCES_URL + item.getApp_pic()).into((ImageView) helper.getView(R.id.iv_more_icon));

        if (PackageUtil.isContains(item.getApp_packge_name())) {
            helper.getView(R.id.fl_down).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.fl_down).setVisibility(View.VISIBLE);
        }
    }

    public void setMoreData(List<MoreBean.DataBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


}
