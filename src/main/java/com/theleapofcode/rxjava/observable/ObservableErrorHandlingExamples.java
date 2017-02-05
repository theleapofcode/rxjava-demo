package com.theleapofcode.rxjava.observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class ObservableErrorHandlingExamples {

	private static void onError() {
		Observable<Integer> ob = Observable.just("1", "two", "3").map(x -> Integer.parseInt(x));

		ob.subscribe((x) -> System.out.println(x), (t) -> System.out.println("Error... " + t.getMessage()),
				() -> System.out.println("Done..."));
	}

	private static void onErrorResumeNext() {
		Observable<Integer> ob = Observable.just("1", "two", "3").map(x -> Integer.parseInt(x))
				.onErrorResumeNext(Observable.just(-1));

		ob.subscribe((x) -> System.out.println(x), (t) -> System.out.println("Error... " + t.getMessage()),
				() -> System.out.println("Done..."));
	}

	private static void retry() {
		Observable<Integer> ob = Observable.just("1", "two", "3").map(x -> Integer.parseInt(x)).retry(1);

		ob.subscribe((x) -> System.out.println(x), (t) -> System.out.println("Error... " + t.getMessage()),
				() -> System.out.println("Done..."));
	}

	private static void retryWhen() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Integer> ob = Observable.just("1", "two", "3").map(x -> Integer.parseInt(x))
				.retryWhen(x -> Observable.timer(1, TimeUnit.SECONDS));

		ob.subscribe((x) -> System.out.println(x), (t) -> System.out.println("Error... " + t.getMessage()), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	private static void retryWithDelay(int numberOfRetries, long delayInSeconds) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Observable<Integer> ob = Observable.just("1", "2", "three", "4").map(x -> Integer.parseInt(x))
				.retryWhen(x -> x.zipWith(Observable.range(1, numberOfRetries), (t, retryCount) -> {
					System.out.println("RetryCount - " + retryCount);
					return retryCount;
				}).flatMap(y -> Observable.timer(delayInSeconds, TimeUnit.SECONDS)));

		ob.subscribe((x) -> System.out.println(x), (t) -> System.out.println("Error... " + t.getMessage()), () -> {
			latch.countDown();
			System.out.println("Done...");
		});
		latch.await();
	}

	public static void main(String[] args) throws InterruptedException {
		// onError();
		// onErrorResumeNext();
		// retry();
		// retryWhen();
		retryWithDelay(2, 3);
	}

}
