package com.taowei.opinions.ui.adapter;

import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taowei.opinions.R;
import com.taowei.opinions.data.LabeBean;

import java.util.List;

public class LabeAdapter extends BaseQuickAdapter<LabeBean.DataBean, BaseViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private int clickTemp = -1;

    //标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }


    public LabeAdapter(int layoutResId, @Nullable List<LabeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabeBean.DataBean item) {

        helper.setText(R.id.tv_labe, item.getType_name());
        //初始化点击小伙
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            helper.getView(R.id.rl_labe).setBackground(mContext.getResources().getDrawable(R.drawable.labe_button));
            TextView tvLabe = helper.getView(R.id.tv_labe);
            tvLabe.setTextColor(mContext.getResources().getColor(R.color.black));
        }

        //点击后变色
        if (clickTemp == helper.getPosition()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                helper.getView(R.id.rl_labe).setBackground(mContext.getResources().getDrawable(R.drawable.labe_button_check));
                TextView tvLabe = helper.getView(R.id.tv_labe);
                tvLabe.setTextColor(mContext.getResources().getColor(R.color.green));

            }
        }


    }

    public void setLabeData(List<LabeBean.DataBean> labeBeans) {
        this.mData = labeBeans;
        notifyDataSetChanged();

    }

}
