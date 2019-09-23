package com.zm.daggerapp.network.main

import com.zm.daggerapp.model.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>
}