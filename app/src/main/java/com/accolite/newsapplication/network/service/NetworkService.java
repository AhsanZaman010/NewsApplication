package com.accolite.newsapplication.network.service;

import com.accolite.newsapplication.model.news.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("v1/search")
    Observable<NewsResponse> getNews(@Query("query") String query);

}
