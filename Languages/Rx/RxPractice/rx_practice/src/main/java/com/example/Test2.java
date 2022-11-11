package com.example;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Test2 {

    private static Observable<Object> stream1;
    private static Observable<Object> stream2;

    public static void main(String[] args) {


        stream1 = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                System.out.println("* stream1 OnSubscribe");
            }
        }).subscribeOn(Schedulers.computation());


        stream1.subscribe(new Subscriber<Object>() {
            @Override
            public void onNext(Object o) {
                System.out.println("* stream1 onNext");
            }

            @Override
            public void onCompleted() {
                System.out.println("* stream1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("* stream1 onError");
            }
        });

        stream2 = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (; ; ) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(new Object());
                }
            }
        }).subscribeOn(Schedulers.computation());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(13000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("13000");

                Observable<Object> stream3 = stream1.mergeWith(stream2);

                stream2.subscribe(new Subscriber<Object>() {
                    @Override
                    public void onNext(Object o) {
                        System.out.println("** stream2 onNext");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("** stream2 onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("** stream2 onNext");
                    }
                });


            }
        }).start();


        for (; ; ) {
        }
    }

}
