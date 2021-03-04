package com.danny.myapplication

import androidx.lifecycle.LiveData
import com.danny.myapplication.data.Post
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/posts?userId=1200")
    suspend fun getPosts() : Response<Post>
}