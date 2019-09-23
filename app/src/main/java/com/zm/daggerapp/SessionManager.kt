package com.zm.daggerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.zm.daggerapp.model.User
import com.zm.daggerapp.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val cachedUser = MediatorLiveData<AuthResource<User?>>()

    fun authenticateWithId(source: LiveData<AuthResource<User?>>) {
        cachedUser.value = AuthResource.loading(User.nullable())
        cachedUser.addSource(source) { resource ->
            cachedUser.value = resource
            cachedUser.removeSource(source)
        }
    }

    fun logout(){
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() = cachedUser
}