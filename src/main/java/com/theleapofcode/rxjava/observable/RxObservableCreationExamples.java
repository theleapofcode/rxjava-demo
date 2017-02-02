package com.theleapofcode.rxjava.observable;

import rx.Observable;

public class RxObservableCreationExamples {

	public static void main(String[] args) {
		Observable<String> ob = Observable.<String>create(s -> {
			System.out.println(Thread.currentThread().getName() + " : " + "Subscribed");
			s.onNext("dileep");
			s.onNext("sangeeth");
			s.onCompleted();
		});

		System.out.println(Thread.currentThread().getName() + " : " + "Before");
		ob.map(str -> str.toUpperCase()).subscribe(
				(msg) -> System.out.println(Thread.currentThread().getName() + " : " + msg + " : 1"),
				(t) -> t.printStackTrace(),
				() -> System.out.println(Thread.currentThread().getName() + " : " + "Completed" + " : 1"));
		ob.map(str -> str.toUpperCase()).subscribe(
				(msg) -> System.out.println(Thread.currentThread().getName() + " : " + msg + " : 2"),
				(t) -> t.printStackTrace(),
				() -> System.out.println(Thread.currentThread().getName() + " : " + "Completed" + " : 2"));
		System.out.println(Thread.currentThread().getName() + " : " + "After");
	}

}
