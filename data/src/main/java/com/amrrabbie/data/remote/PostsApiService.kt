package com.amrrabbie.data.remote

import com.amrrabbie.domain.entity.PostsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApiService {

    @GET("everything")
    suspend fun getPosts(
        @Query("q") q:String,
        @Query("apiKey") apiKey:String,
        @Query("page") page:Int
    ):PostsResponse
}