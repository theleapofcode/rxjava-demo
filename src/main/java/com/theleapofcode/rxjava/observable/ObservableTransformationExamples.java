package com.theleapofcode.rxjava.observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class ObservableTransformationExamples {

	private static void map() {
		Observable<Integer> ob = Observable.just(1, 2, 3).map(x -> x * x);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void flatMap() {
		Observable<Integer> ob = Observable.range(1, 5).flatMap(x -> Observable.range(0, x));

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void concatMap() {
		Observable<Integer> ob = Observable.range(1, 5).concatMap(x -> Observable.range(0, x));

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void zip() {
		Observable<Integer> ob1 = Observable.range(1, 3);
		Observable<Integer> ob2 = Observable.range(4, 3);

		Observable<Integer> ob = Observable.zip(ob1, ob2, (x, y) -> x + y);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void concat() {
		Observable<Integer> ob1 = Observable.range(1, 3);
		Observable<Integer> ob2 = Observable.range(4, 3);

		Observable<Integer> ob = Observable.concat(ob1, ob2);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void filter() {
		Observable<Integer> ob = Observable.range(1, 5).filter(x -> x > 2);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void distinct() {
		Observable<Integer> ob = Observable.just(1, 2, 2, 3).distinct();

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void first() {
		Observable<Integer> ob = Observable.just(1, 2, 3).first();

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void last() {
		Observable<Integer> ob = Observable.just(1, 2, 3).last();

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void take() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Long> ob = Observable.interval(1, TimeUnit.SECONDS).take(5);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	private static void takeTime() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Long> ob = Observable.interval(1, TimeUnit.SECONDS).take(2, TimeUnit.SECONDS);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	private static void reduce() {
		Observable<Integer> ob = Observable.just(1, 2, 3).reduce((x, y) -> x + y);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void scan() {
		Observable<Integer> ob = Observable.just(1, 2, 3).scan((x, y) -> x + y);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	public static void main(String[] args) throws InterruptedException {
		map();
		flatMap();
		concatMap();
		zip();
		concat();
		filter();
		distinct();
		first();
		last();
		take();
		takeTime();
		reduce();
		scan();
	}

}
