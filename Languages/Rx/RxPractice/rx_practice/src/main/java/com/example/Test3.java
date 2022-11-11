package com.example;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class Test3 {

    public static void main(String[] args) {

        PublishSubject<String> ps = PublishSubject.create();
        ps.onNext("1");

        ps.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        ps.onNext("2");



        for(;;){}
    }

}
