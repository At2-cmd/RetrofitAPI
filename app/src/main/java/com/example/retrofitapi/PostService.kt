package com.example.retrofitapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    fun listPost(@Query("userId") userId: String): Call<List<Post>>
}