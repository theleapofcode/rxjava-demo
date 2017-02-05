package com.theleapofcode.rxjava.example;

import rx.Observable;

public class RxFibonaaci {

	static class Fib {
		final int n1;
		final int n2;

		Fib(int n1, int n2) {
			this.n1 = n1;
			this.n2 = n2;
		}
	}

	private static Observable<Integer> fibonacci(final int n) {
		return Observable.range(1, n - 1).scan(new Fib(0, 1), (prev, curr) -> new Fib(prev.n2, prev.n1 + prev.n2))
				.map(fib -> fib.n2);
	}

	public static void main(String[] args) throws InterruptedException {
		int n = 10;

		fibonacci(n).subscribe((x) -> System.out.println(x), (t) -> t.printStackTrace(),
				() -> System.out.println("Done..."));
	}

}
