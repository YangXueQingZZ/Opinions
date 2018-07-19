package com.taowei.opinions.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.LocationClientOption;
import com.taowei.opinions.R;
import com.taowei.opinions.base.BaseActivity;
import com.taowei.opinions.service.FloatingWindowService;
import com.taowei.opinions.ui.fragment.BookFragment;
import com.taowei.opinions.ui.fragment.FragmentFactory;
import com.taowei.opinions.ui.fragment.MeFragment;
import com.taowei.opinions.ui.fragment.MoneyFragment;
import com.taowei.opinions.ui.fragment.NewsFragment;
import com.taowei.opinions.ui.fragment.VideoFragment;
import com.taowei.opinions.ui.widget.MarqueeTextView;
import com.taowei.opinions.ui.widget.RoundImageView;
import com.taowei.opinions.utils.LogUtil;
import com.taowei.opinions.utils.LunarUtils;
import com.taowei.opinions.utils.SharePreferencesHelper;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.taowei.opinions.app.Config.APP_ID_WX;
import static com.taowei.opinions.ui.fragment.FragmentFactory.F1;
import static com.taowei.opinions.ui.fragment.FragmentFactory.F2;
import static com.taowei.opinions.ui.fragment.FragmentFactory.F3;
import static com.taowei.opinions.ui.fragment.FragmentFactory.F4;
import static com.taowei.opinions.ui.fragment.FragmentFactory.F5;

public class MainActivity extends BaseActivity {


    private final String TAG = getClass().getName();
    @BindView(R.id.iv_head)
    RoundImageView ivHead;
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;

    @BindView(R.id.main_title_search)
    ImageView mainTitleSearch;
    @BindView(R.id.main_title_plus)
    ImageView mainTitlePlus;
    @BindView(R.id.main_title_reduce)
    ImageView mainTitleReduce;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_book)
    TextView tvBook;
    @BindView(R.id.iv_triangle)
    ImageView ivTriangle;
    @BindView(R.id.tv_money)
    TextView tvMoney;


    private NewsFragment newsFragment;
    private VideoFragment videoFragment;
    private BookFragment bookFragment;
    private MeFragment meFragment;
    private MoneyFragment moneyFragment;

    private FragmentManager mFragmentManager;

    //记录Fragment的位置
    private int position;

    private Calendar calendar = Calendar.getInstance();
    private LunarUtils lunar = new LunarUtils(calendar);
    private String location;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvTitle.setText(time() + "(" + lunar.toString() + ")" + location); //更新时间
                    break;
            }
        }
    };
    private IWXAPI api;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inView(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        preventOverlap(savedInstanceState);
        location = (String) SharePreferencesHelper.get(MainActivity.this, "location", "");
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, APP_ID_WX, true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID_WX);
        new TimeThread().start(); //启动新的线程

        Intent intent = new Intent(MainActivity.this, FloatingWindowService.class);
        startService(intent);
        tvTitle.setText(time() + "(" + lunar.toString() + ")" + location);
    }


    /**
     * 防止Fragment重叠的问题，切换横竖屏或者内存不够，重走了Activity的生命周期
     * 第一次要createFragment
     * 重启以后，要通过tag来找到对应的Fragment
     * <p>
     * 重启的标准是savedInstanceState是否为null
     * 因为重启之前savedInstanceState=null
     * 重启之后savedInstanceState！=null
     *
     * @param savedInstanceState
     */
    private void preventOverlap(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
//        如果savedInstanceState为空，证明没有发生重走Activity的生命周期的情况，这时候要创建createFragment
            meFragment = (MeFragment) FragmentFactory.createFragment(F1);
            tvMe.setTextColor(getResources().getColor(R.color.green));
            selectFragment(meFragment);

            LogUtil.i(TAG, "savedInstanceState is  null");
        } else {
            LogUtil.i(TAG, "savedInstanceState is not null");
//            使用mFragmentManager通过Tag来取得，只要他add过，就给他添加了Tag
//            否则直接重写创建一个Fragment的话,会导致重叠
            meFragment = (MeFragment) mFragmentManager.findFragmentByTag(MeFragment.class.getName());
            newsFragment = (NewsFragment) mFragmentManager.findFragmentByTag(NewsFragment.class.getName());
            videoFragment = (VideoFragment) mFragmentManager.findFragmentByTag(VideoFragment.class.getName());
            bookFragment = (BookFragment) mFragmentManager.findFragmentByTag(BookFragment.class.getName());
            moneyFragment = (MoneyFragment) mFragmentManager.findFragmentByTag(MoneyFragment.class.getName());
//=======================以下代码，不要系统也会自动识别，上次死亡位置，但是除了死亡位置，savedInstanceState还可以传递其他数据============
//            获得上次死亡重启的位置
            position = savedInstanceState.getInt("position");
            LogUtil.i(TAG, position + "=======");
            switch (position) {
                case 1:
                    selectFragment(meFragment);
                    break;
                case 2:
                    selectFragment(newsFragment);
                    break;
                case 3:
                    selectFragment(videoFragment);
                    break;
                case 4:
                    selectFragment(bookFragment);
                    break;
                case 5:
                    selectFragment(moneyFragment);
                    break;
            }
            //===================以上代码需要配合onSaveInstanceState（）方法里面记录数据================
        }
    }

    /**
     * 底部导航栏
     *
     * @param view
     */
    @OnClick({R.id.rl_me, R.id.rl_news, R.id.rl_video, R.id.rl_book, R.id.rl_money})
    public void viewClick(View view) {
        setBottomBackground();
        switch (view.getId()) {
            case R.id.rl_me:

                if (meFragment == null) {
                    meFragment = (MeFragment) FragmentFactory.createFragment(F1);

                }
                tvMe.setTextColor(getResources().getColor(R.color.green));
                selectFragment(meFragment);
                position = 1;

                break;
            case R.id.rl_news:

                if (newsFragment == null) {
                    newsFragment = (NewsFragment) FragmentFactory.createFragment(F2);

                }
                tvNews.setTextColor(getResources().getColor(R.color.green));
                selectFragment(newsFragment);
                position = 2;

                break;

            case R.id.rl_video:
                if (videoFragment == null) {
                    videoFragment = (VideoFragment) FragmentFactory.createFragment(F3);

                }
                tvVideo.setTextColor(getResources().getColor(R.color.green));
                selectFragment(videoFragment);
                position = 3;

                break;

            case R.id.rl_book:
                if (bookFragment == null) {
                    bookFragment = (BookFragment) FragmentFactory.createFragment(F4);

                }
                tvBook.setTextColor(getResources().getColor(R.color.green));
                ivTriangle.setImageResource(R.mipmap.greentriangle);
                selectFragment(bookFragment);
                position = 4;

                break;

            case R.id.rl_money:
                if (moneyFragment == null) {

                    moneyFragment = (MoneyFragment) FragmentFactory.createFragment(F5);

                }
                tvMoney.setTextColor(getResources().getColor(R.color.green));
                selectFragment(moneyFragment);
                position = 5;

                break;
        }
    }

    /**
     * 选择某一个fragment
     *
     * @param fragment
     */
    private void selectFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        hideAll(transaction);

        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment, fragment.getClass().getName());
            transaction.addToBackStack(null);//加入回退栈
        }
        transaction.show(fragment).commit();

    }

    private void hideAll(FragmentTransaction transaction) {

        if (meFragment != null) {
//            必须使用同一个transaction，跟add/show时候一样
//            同一个事物进行了所有的add/hide/show，之后统一commit就行了
//            中间不能commit事物，因为一个事物只能是提交一次，重复提交会报错
//            即：每一次点击，生成一个事务，操作完后，提交这个事务
            transaction.hide(meFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (videoFragment != null) {
            transaction.hide(videoFragment);
        }
        if (bookFragment != null) {
            transaction.hide(bookFragment);
        }
        if (moneyFragment != null) {
            transaction.hide(moneyFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
        LogUtil.i(TAG, position + "outState");
        super.onSaveInstanceState(outState);
    }


    private void setBottomBackground() {
        ivTriangle.setImageResource(R.mipmap.triangle);
        tvMe.setTextColor(getResources().getColor(R.color.black));
        tvNews.setTextColor(getResources().getColor(R.color.black));
        tvVideo.setTextColor(getResources().getColor(R.color.black));
        tvBook.setTextColor(getResources().getColor(R.color.black));
        tvMoney.setTextColor(getResources().getColor(R.color.black));
    }


    @OnClick({R.id.iv_head, R.id.main_title_search, R.id.main_title_plus, R.id.main_title_reduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                loginByWebchat();
                break;
            case R.id.main_title_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                finish();
                break;
            case R.id.main_title_plus:

                break;
            case R.id.main_title_reduce:

                break;
        }
    }
    /**
     * 微信登录调用此方法.
     */
    public void loginByWebchat() {


        if (api != null && api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "login_state";
            api.sendReq(req);
        }
    }

    /**
     * 时间
     */
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {


                    Thread.sleep(60000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    handler.sendMessage(msg);// 每隔1分钟发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private CharSequence time() {
        long sysTime = System.currentTimeMillis();//获取系统时间
        CharSequence sysTimeStr = DateFormat.format("hh:mm", sysTime);//时间显示格式
        return sysTimeStr;
    }

}
