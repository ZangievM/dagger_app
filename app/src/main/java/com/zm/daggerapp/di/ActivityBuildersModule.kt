package com.zm.daggerapp.di

import com.zm.daggerapp.di.auth.AuthModule
import com.zm.daggerapp.di.auth.AuthScope
import com.zm.daggerapp.di.auth.AuthViewModelsModule
import com.zm.daggerapp.di.main.MainFragmentBuildersModule
import com.zm.daggerapp.di.main.MainModule
import com.zm.daggerapp.di.main.MainScope
import com.zm.daggerapp.di.main.MainViewModelsModule
import com.zm.daggerapp.ui.auth.AuthActivity
import com.zm.daggerapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}