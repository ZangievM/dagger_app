package com.zm.daggerapp.di.auth

import androidx.lifecycle.ViewModel
import com.zm.daggerapp.di.ViewModelKey
import com.zm.daggerapp.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {
    @AuthScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}