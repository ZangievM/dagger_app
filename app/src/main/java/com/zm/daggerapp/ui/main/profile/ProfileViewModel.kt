package com.zm.daggerapp.ui.main.profile

import androidx.lifecycle.ViewModel;
import com.zm.daggerapp.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {
    fun getAuthenticatedUser() = sessionManager.getAuthUser()

}
