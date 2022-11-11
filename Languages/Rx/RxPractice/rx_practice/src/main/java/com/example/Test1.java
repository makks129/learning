package com.example;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class Test1 {

    public static void main(String[] args) {

        // Observable

        Observable<String> just = Observable.just("Hello World!");
        Observable<String> from = Observable.from(new String[]{"one", "two", "three"});
        Observable<Integer> range = Observable.range(0, 100);
        Observable<Long> timer = Observable.timer(1, TimeUnit.SECONDS);
        Observable<Long> interval = Observable.interval(60, TimeUnit.SECONDS);
        Observable<Long> empty = Observable.empty();
        Observable<String> custom = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext("Hello World!");
                    subscriber.onCompleted();
                } catch (Throwable t) {
                    subscriber.onError(t);
                }
            }
        });


        // Observer

        Observable.from(new String[]{"one", "two", "three"})
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        // onNext
                        System.out.println(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // onError
                        System.out.println("Error!");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        // onCompleted
                        System.out.println("Done!");
                    }
                });

        Observable.from(new String[]{"one", "two", "three"})
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error!");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Done!");
                    }
                });


        // Subscriber

        Observable<String> stream = Observable.from(new String[]{"one", "two", "three"});

        Subscription subscription = stream.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error!");
            }

            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }
        });
        subscription.unsubscribe();


        // Custom Observable + Subscriber

        Observable<Integer> streamOfNumbers = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {

                    for (int i = 0; i < 100; i++) {
                        subscriber.onNext(i);
                    }

                    subscriber.onCompleted();

                } catch (Throwable t) {
                    subscriber.onError(t);
                }
            }
        });

        Subscription subscription1 = streamOfNumbers.subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("Next number is: " + integer);
            }

            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error!");
            }
        });


        // Thinking in streams 1

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                if (i == 50) {
                    System.out.println("Checkpoint!");
                }
            }
        }


        Observable.range(0, 100)
                .filter(i -> i % 2 == 0)
                .doOnNext(integer ->
                        Observable.just(integer)
                                .filter(i1 -> i1 == 50)
                                .forEach(i1 -> System.out.println("Checkpoint!")))
                .forEach(System.out::println);


        // Thinking in streams 1

        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(Observable.timer(60, TimeUnit.SECONDS));




        // Operators

        Observable.range(0, 100)
                .filter(i -> i % 10 == 0)
                .limit(5)
                .map(String::valueOf)
                .scan((s, s2) -> s + s2);







    }

}
