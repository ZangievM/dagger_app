package com.zm.daggerapp.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zm.daggerapp.R
import com.zm.daggerapp.util.baseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        @Singleton
        @Provides
        @JvmStatic
        fun provideRequestOptions() = RequestOptions
            .placeholderOf(android.R.drawable.ic_secure)
            .error(android.R.drawable.ic_media_ff)

        @Singleton
        @Provides
        @JvmStatic
        fun provideGlideInstance(app: Application, options: RequestOptions) =
            Glide.with(app)
                .setDefaultRequestOptions(options)

        @Singleton
        @Provides
        @JvmStatic
        fun provideApplicationDrawable(app: Application): Drawable =
            ContextCompat.getDrawable(app, R.mipmap.ic_launcher)!!
    }
}