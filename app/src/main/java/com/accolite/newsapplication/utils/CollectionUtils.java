package com.accolite.newsapplication.utils;

import java.util.List;
public class CollectionUtils {

    public static synchronized boolean isEmpty(List list){
        if(null == list || list.size()==0){
            return true;
        }
        return false;
    }

}
