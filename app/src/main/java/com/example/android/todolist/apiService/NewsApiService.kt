package com.example.android.todolist.apiService

import com.example.android.todolist.constants.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query(value = "country") us: String = "us",
        @Query(value = "apiKey") apiKey: String = AppConstants.API_KEY
    )

    @GET("/v2/everything")
    suspend fun getAllNews(
        @Query(value = "q") query: String,
        @Query(value = "apiKey") apiKey: String = AppConstants.API_KEY
    )

}