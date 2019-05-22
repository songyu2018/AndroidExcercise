package com.yusong.photosearch.services;

import com.yusong.photosearch.Models.SearchResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface ApiService {

    /*
    Retrofit get annotation with our URL
    */
    @GET("services/rest")

    Call<SearchResult> getSearchResult(@QueryMap Map<String, String> params);
}
