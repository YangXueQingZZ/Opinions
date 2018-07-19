package com.taowei.opinions.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.taowei.opinions.R;
import com.taowei.opinions.base.BaseFragment;
import com.taowei.opinions.data.LabeBean;
import com.taowei.opinions.net.ActivityLifeCycleEvent;
import com.taowei.opinions.net.BaseRetrofit;
import com.taowei.opinions.net.HttpUtil;
import com.taowei.opinions.net.callback.LabeCallBack;
import com.taowei.opinions.ui.adapter.LabeAdapter;
import com.taowei.opinions.utils.DeviceUtil;
import com.taowei.opinions.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

import static com.taowei.opinions.app.Config.ARG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {


    @BindView(R.id.main_labe)
    RecyclerView mainLabe;
    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    @BindView(R.id.recycler_more)
    RecyclerView recyclerMore;


    private List<LabeBean.DataBean> labeBeans = new ArrayList<>();
    private LabeAdapter labeAdapter;
    private Observable observable;

    public static MeFragment newInstance(String args) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG, args);
        MeFragment meFragment = new MeFragment();

        meFragment.setArguments(bundle);
        return meFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void inData() {

        setLabe();
    }

    /**
     *
     */
    private void setLabe() {

        reclylerManager(mainLabe, 4);
        labeAdapter = new LabeAdapter(R.layout.labe_item, labeBeans);
        mainLabe.setAdapter(labeAdapter);
        getLabeData(labeAdapter);
        labeAdapter.setSeclection(0);

        labeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {

                    recyclerMain.setVisibility(View.VISIBLE);
                    recyclerMore.setVisibility(View.GONE);
                } else {

                    recyclerMain.setVisibility(View.GONE);
                    recyclerMore.setVisibility(View.VISIBLE);

                }

                labeAdapter.setSeclection(position);
                labeAdapter.notifyDataSetChanged();
                LogUtil.i("LABE", position + "<-------------");
            }
        });

    }


    /**
     * 初始化RecyclerView 行数
     *
     * @param recyclerView
     * @param count
     */
    private void reclylerManager(RecyclerView recyclerView, int count) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(count, StaggeredGridLayoutManager.VERTICAL));
    }

    /**
     * 获取标签
     *
     * @param adapter
     */
    private void getLabeData(final LabeAdapter adapter) {

        observable = BaseRetrofit.getDefault().getLabe(DeviceUtil.getDeviceUuid().toString());
        HttpUtil.getInstance().toSubscribe(observable, ActivityLifeCycleEvent.DESTROY, lifecycleSubject, new LabeCallBack() {
            @Override
            public void onErrors(Throwable e) {
                LogUtil.d("", "没网络");

            }

            @Override
            public void onResponse(LabeBean response) {

                labeBeans = response.getData();
                adapter.setLabeData(labeBeans);

            }
        });

    }
}
