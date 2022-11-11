package com.example;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

public class TestPublishRefcountShare {

    private static Subscription sub1;
    private static Subscription sub2;

    private static Subscription refCountSub1;
    private static Subscription refCountSub2;

    public static void main(String[] args) {

//        Observable<Long> stream = Observable.interval(1, TimeUnit.SECONDS).doOnNext(System.out::println);
//        stream.subscribe();


        //

//        ConnectableObservable<Long> publishStream = Observable.interval(1, TimeUnit.SECONDS)
//                .doOnNext(System.out::println)
//                .publishStream();
//        publishStream.subscribe();
//        publishStream.connect();


        //


//        ConnectableObservable<Long> publishStream = Observable.interval(1, TimeUnit.SECONDS)
//                .doOnNext(System.out::println)
//                .publishStream();
//        publishStream.connect();
//
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1 = publishStream.subscribe(aLong1 -> {
//                System.out.println("sub1 onNext: " + aLong1);
//            });
//        });
//
//        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1.unsubscribe();
//        });


        //


//        ConnectableObservable<Long> publishStream = Observable.interval(1, TimeUnit.SECONDS)
//                .doOnNext(System.out::println)
//                .publishStream();
//        Subscription conStreamSub = publishStream.connect();
//
//
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1 = publishStream.subscribe(aLong1 -> {
//                System.out.println("sub1 onNext: " + aLong1);
//            });
//        });
//
//        Observable.timer(7, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub2 = publishStream.subscribe(aLong1 -> {
//                System.out.println("sub2 onNext: " + aLong1);
//            });
//        });
//
//        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1.unsubscribe();
//        });
//
//        Observable.timer(12, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub2.unsubscribe();
//        });
//
//        Observable.timer(15, TimeUnit.SECONDS).subscribe(aLong -> {
//            conStreamSub.unsubscribe();
//        });


        //


//        ConnectableObservable<Long> publishStream = Observable.interval(1, TimeUnit.SECONDS)
//                .doOnNext(System.out::println)
//                .publish();
//        publishStream.connect();
//
//        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1 = publishStream.subscribe(aLong1 -> {
//                System.out.println("sub1 onNext: " + aLong1);
//            });
//        });
//
//        Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub2 = publishStream.subscribe(aLong1 -> {
//                System.out.println("sub2 onNext: " + aLong1);
//            });
//        });
//
//        Observable.timer(4, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub1.unsubscribe();
//        });
//
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> {
//            sub2.unsubscribe();
//        });
//
//        Observable<Long> refCountStream = publishStream.refCount();
//
//
//        Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
//            refCountSub = refCountStream.subscribe(aLong1 -> {
//                System.out.println("refCountStream: " + aLong1);
//            });
//        });
//
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> {
//            refCountSub.unsubscribe();
//        });


        //


        Observable<Long> stream = Observable.interval(1, TimeUnit.SECONDS)
                .doOnNext(System.out::println)
                .publish()
                .refCount();

        Observable.timer(1, TimeUnit.SECONDS).subscribe(aLong -> {
            refCountSub1 = stream.subscribe(aLong1 -> {
                System.out.println("refCountSub1: " + aLong1);
            });
        });

        Observable.timer(4, TimeUnit.SECONDS).subscribe(aLong -> {
            refCountSub2 = stream.subscribe(aLong1 -> {
                System.out.println("refCountSub2: " + aLong1);
            });
        });

        Observable.timer(8, TimeUnit.SECONDS).subscribe(aLong -> {
            refCountSub1.unsubscribe();
        });


        Observable.timer(12, TimeUnit.SECONDS).subscribe(aLong -> {
            refCountSub2.unsubscribe();
        });






















































        for(;;) {}

    }

}
