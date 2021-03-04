package com.danny.myapplication

import androidx.lifecycle.LiveData
import com.danny.myapplication.data.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/posts")
    suspend fun getPosts(@Query("userId") id : Int) : Response<Post>
}