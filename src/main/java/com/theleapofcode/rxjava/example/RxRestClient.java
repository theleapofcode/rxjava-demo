package com.theleapofcode.rxjava.example;

import java.util.List;

import com.theleapofcode.rxjava.model.Repo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RxRestClient {

	public static Observable<List<Repo>> listRepos(String user) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create()).build();

		GitHubService service = retrofit.create(GitHubService.class);

		return service.listRepos(user);
	}

	public static void main(String[] args) {
		listRepos("theleapofcode").subscribe((x) -> x.forEach(System.out::println), (t) -> t.printStackTrace(),
				() -> System.out.println("Done..."));
	}

}
