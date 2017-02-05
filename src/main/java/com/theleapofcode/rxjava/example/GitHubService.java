package com.theleapofcode.rxjava.example;

import java.util.List;

import com.theleapofcode.rxjava.model.Repo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {
	@GET("users/{user}/repos")
	Observable<List<Repo>> listRepos(@Path("user") String user);
}
