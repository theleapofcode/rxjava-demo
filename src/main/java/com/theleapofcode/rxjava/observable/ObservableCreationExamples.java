package com.theleapofcode.rxjava.observable;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class ObservableCreationExamples {

	private static void createObservableFromCreate() {
		Observable<String> ob = Observable.<String>create(s -> {
			s.onNext("IronMan");
			s.onNext("CaptainAmerica");
			s.onNext("Hulk");
			s.onCompleted();
		});

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void createObservableFromFrom() {
		Observable<String> ob = Observable.from(Arrays.asList("IronMan", "CaptainAmerica", "Hulk"));

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void createObservableFromJust() {
		Observable<String> ob = Observable.just("IronMan", "CaptainAmerica", "Hulk");

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void createObservableFromRange() {
		Observable<Integer> ob = Observable.range(3, 5);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void createObservableFromInterval() throws InterruptedException {
		// interval is asynchronous. Hold the thread till observable yields
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Long> ob = Observable.interval(1, TimeUnit.SECONDS).take(5);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	private static void createObservableFromTimer() throws InterruptedException {
		// timer is asynchronous. Hold the thread till observable yields
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Long> ob = Observable.timer(1, TimeUnit.SECONDS);

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	private static void createObservableFromEmpty() throws InterruptedException {
		Observable<Long> ob = Observable.empty();

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	private static void createObservableFromError() throws InterruptedException {
		Observable<Long> ob = Observable.error(new RuntimeException("My error"));

		ob.subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(), () -> System.out.println("Done..."));
	}

	public static void main(String[] args) throws InterruptedException {
		createObservableFromCreate();
		createObservableFromFrom();
		createObservableFromJust();
		createObservableFromRange();
		createObservableFromInterval();
		createObservableFromTimer();
		createObservableFromEmpty();
		createObservableFromError();
	}

}
