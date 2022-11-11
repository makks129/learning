package com.example;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;

public class TestDataFlow1 {

  public static void main(String[] args) {

    /**
     * 1: parallel
     */

    //Observable.interval(1, TimeUnit.SECONDS).take(3).subscribe(l -> {
    //  System.out.println("First: " + l);
    //});
    //
    //Observable.interval(1, TimeUnit.SECONDS)
    //    .delay(500, TimeUnit.MILLISECONDS)
    //    .take(3)
    //    .subscribe(l -> {
    //      System.out.println("Second: " + l);
    //    });

    /**
     * 2: withLatestFrom
     */

    Observable<Long> second =
        Observable.interval(1, TimeUnit.SECONDS).delay(500, TimeUnit.MILLISECONDS).take(5);

    Observable.interval(1, TimeUnit.SECONDS)
        .take(2)
        .withLatestFrom(second, (l1, l2) -> "First: " + l1 + " Second: " + l2)
        .subscribe(new Subscriber<String>() {
          @Override public void onNext(String s) {
            System.out.println("onNext | " + s);
          }

          @Override public void onCompleted() {
            System.out.println("onCompleted | ");
          }

          @Override public void onError(Throwable e) {
            System.out.println("onError | " + e);
          }
        });

    /**
     * 3: combineLatest
     */

    //Observable<Long> first = Observable.interval(1, TimeUnit.SECONDS).take(3);
    //
    //Observable<Long> second =
    //    Observable.interval(1, TimeUnit.SECONDS).delay(500, TimeUnit.MILLISECONDS).take(5);
    //
    //Observable.combineLatest(first, second, (l1, l2) -> "First: " + l1 + " Second: " + l2)
    //    .subscribe(new Subscriber<String>() {
    //      @Override public void onNext(String s) {
    //        System.out.println("onNext | " + s);
    //      }
    //
    //      @Override public void onCompleted() {
    //        System.out.println("onCompleted | ");
    //      }
    //
    //      @Override public void onError(Throwable e) {
    //        System.out.println("onError | " + e);
    //      }
    //    });


    //
    //
    //
    //
    //
    terminate();
  }

  private static void terminate() {
    // termination
    Observable.timer(10, TimeUnit.SECONDS).subscribe(l -> {
      System.exit(0);
    });
    for (; ; ) {
    }
  }
}
