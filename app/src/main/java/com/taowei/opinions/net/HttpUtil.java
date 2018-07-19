package com.taowei.opinions.net;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;

public class HttpUtil {


    private static  final String TAG ="HttpUtil";

    private HttpUtil(){}
    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }
    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 添加线程管理并订阅
     * @param ob
     * @param subscriber
     * @param event Activity 生命周期
     * @param lifecycleSubject
     */
    public void toSubscribe(Observable ob, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, final DataCallback subscriber) {
        //数据预处理
        ObservableTransformer<String, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        Observable observable = ob.compose(result);
        //不需要缓存
        observable.subscribe(subscriber);
        //缓存
//        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);
    }
}
