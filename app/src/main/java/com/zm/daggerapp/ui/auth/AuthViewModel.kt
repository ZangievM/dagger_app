package com.zm.daggerapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.zm.daggerapp.SessionManager
import com.zm.daggerapp.model.User
import com.zm.daggerapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi, private val sessionManager: SessionManager) :
    ViewModel() {


    fun getUserState(): LiveData<AuthResource<User?>> = sessionManager.getAuthUser()
    private fun queryUserId(id: Int) = LiveDataReactiveStreams.fromPublisher(
        authApi.getUser(id)
            .subscribeOn(Schedulers.io())
            .onErrorReturn {
                User()
            }
            .map { user ->
                if (user.id == -1) {
                    AuthResource.error("Could not authenticate", User.nullable())
                } else
                    AuthResource.authenticated(user)
            })

    fun authenticateWithId(id: Int) {
        val liveData = queryUserId(id)
        sessionManager.authenticateWithId(liveData)
    }
}