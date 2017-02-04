package com.theleapofcode.rxjava.observable;

import rx.Observable;

public class ObservableTransformationExamples {

	private static void map() {
		Observable<Integer> ob = Observable.just(1, 2, 3).map(x -> x * x);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	public static void main(String[] args) throws InterruptedException {
		map();
	}

}
