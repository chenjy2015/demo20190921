package com.example.myapplication;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaDemo {

    private RxJavaDemo instance;

    private RxJavaDemo() {

    }

    private static class Singleton {
        private static final RxJavaDemo singleton = new RxJavaDemo();
    }

    public static RxJavaDemo getInstance() {
        return Singleton.singleton;
    }

    public void init() {
        queue();
    }

    public void queue() {
//        Observable.just(1,2,3).subscribe(System.out::println);
//        Observable.range(0,10).map(String::valueOf).forEach(System.out::println);
//        Observable.interval(3, TimeUnit.SECONDS).subscribe(this::log);
//        Observable.intervalRange(0,10,1,1,TimeUnit.SECONDS).subscribe(this::log);
//        Observable.create(emitter -> {
//            emitter.onNext(1);
//            emitter.onNext(2);
//            emitter.onComplete();
//        }).subscribe(this::log);
//        Observable observable =  Observable.defer(() -> Observable.just(System.currentTimeMillis()));
//        observable.subscribe(this::log);
//        System.out.println();
//        observable.subscribe(this::log);
//        Observable.<String>empty().subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                log("onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                log("onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                log("onComplete: ");
//            }
//        });
//        Observable.<String>never().subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                log("onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                log("onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                log("onComplete: ");
//            }
//        });
//        Observable.error(new NullPointerException("the object can not be null!")).subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                log("onSubscribe: " + d);
//            }
//
//            @Override
//            public void onNext(Object o) {
//                log("onNext: " + o);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                log("onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                log("onComplete: ");
//            }
//        });
//        Observable.fromArray(new String[]{"1","2","3"}).subscribe(this::log);
//        Observable.fromCallable(() -> 1000L).flatMap(o -> Observable.just(o)).subscribe(this::log);
    }

    public void log(Object o) {
        Log.d("RxJavaDemo", o.toString());
    }
}
