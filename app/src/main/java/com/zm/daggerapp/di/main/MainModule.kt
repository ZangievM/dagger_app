package com.zm.daggerapp.di.main

import com.zm.daggerapp.network.main.MainApi
import com.zm.daggerapp.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @MainScope
        fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

        @JvmStatic
        @Provides
        @MainScope
        fun provideAdapter():PostsRecyclerAdapter = PostsRecyclerAdapter()
    }
}