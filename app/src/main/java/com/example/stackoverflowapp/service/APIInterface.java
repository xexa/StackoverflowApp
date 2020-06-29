package com.example.stackoverflowapp.service;

import com.example.stackoverflowapp.model.StackApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("answers")
    Call<StackApiResponse> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int pageSize,
            @Query("order") String order,
            @Query("sort") String sort,
            @Query("site") String site);
}
