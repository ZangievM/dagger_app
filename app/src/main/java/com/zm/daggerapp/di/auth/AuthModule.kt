package com.zm.daggerapp.di.auth

import com.zm.daggerapp.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }

}