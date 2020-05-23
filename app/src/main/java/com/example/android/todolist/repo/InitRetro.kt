package com.example.android.todolist.repo

import com.example.android.todolist.apiService.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class InitRetro {

    private val BASE_URL = "https://newsapi.org/"

    private val logging = HttpLoggingInterceptor()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()

    var apiService = retrofit.create(NewsApiService::class.java)

}
