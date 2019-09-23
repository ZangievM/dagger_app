package com.zm.daggerapp.network.auth

import com.zm.daggerapp.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User?>
}