package com.accolite.newsapplication.ui.module.news.view;

import com.accolite.newsapplication.model.news.Hit;
import com.accolite.newsapplication.ui.base.BaseView;

import java.util.List;

public interface NewsView extends BaseView{
    void updateNews(List<Hit> items);

    void showContent();

    void hideContent();

    void showNoDataFound();

    void openWebPage(Hit item);
}
