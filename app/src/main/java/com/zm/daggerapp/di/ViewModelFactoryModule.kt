package com.zm.daggerapp.di

import androidx.lifecycle.ViewModelProvider
import com.zm.daggerapp.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}