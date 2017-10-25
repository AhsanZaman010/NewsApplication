package com.accolite.newsapplication.repository;


import com.accolite.newsapplication.model.news.NewsResponse;

public interface ILocalRepository {

    NewsResponse getNews(String query);
}
