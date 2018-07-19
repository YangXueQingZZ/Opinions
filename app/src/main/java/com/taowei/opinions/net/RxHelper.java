package com.taowei.opinions.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxHelper {


    /**
     * 利用Observable.takeUntil()停止网络请求
     *
     * @param event
     * @param lifecycleSubject
     * @param <T>
     * @return
     */
    public <T> ObservableTransformer<T, T> bindUntilEvent(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.filter(new Predicate<ActivityLifeCycleEvent>() {
                            @Override
                            public boolean test(ActivityLifeCycleEvent activityLifeCycleEvent) throws Exception {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return observable.takeUntil(compareLifecycleObservable);
            }
        };
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<String, T> handleResult(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<String, T>() {
            @Override
            public ObservableSource<T> apply(Observable<String> observable) {

                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.filter(new Predicate<ActivityLifeCycleEvent>() {
                            @Override
                            public boolean test(ActivityLifeCycleEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return (ObservableSource<T>) observable.takeUntil(compareLifecycleObservable).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
