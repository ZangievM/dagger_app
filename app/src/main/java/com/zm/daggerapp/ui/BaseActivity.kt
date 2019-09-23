package com.zm.daggerapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.zm.daggerapp.SessionManager
import com.zm.daggerapp.ui.auth.AuthActivity
import com.zm.daggerapp.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer { user ->
            when (user.status) {
                AuthResource.AuthStatus.AUTHENTICATED -> {
                }
                AuthResource.AuthStatus.ERROR -> {
                }
                AuthResource.AuthStatus.LOADING -> {}
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    navToLogin()
                }
            }
        })
    }

    private fun navToLogin() {
        startActivity(Intent(this,AuthActivity::class.java))
        finish()
    }
}