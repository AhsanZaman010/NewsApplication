package com.accolite.newsapplication.utils;

import android.text.TextUtils;

public class NewsUtils {

    private static final String AUTHOR_STRING = "Author: ";

    public static synchronized String getReadableString(String string){
        if(TextUtils.isEmpty(string)){
            return "-";
        } else {
            return string;
        }
    }

    public static String getAuthorString(String author) {
        author = getReadableString(author);
        return AUTHOR_STRING + author;
    }
}
