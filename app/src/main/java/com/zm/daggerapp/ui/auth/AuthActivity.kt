package com.zm.daggerapp.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.zm.daggerapp.R
import com.zm.daggerapp.ui.main.MainActivity
import com.zm.daggerapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"
    lateinit var viewModel: AuthViewModel
    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var drawable: Drawable
    @Inject
    lateinit var glideInstance: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        subscribeObservers()
        login_button.setOnClickListener {
            attemptToLogin()
        }

    }

    private fun subscribeObservers() {
        viewModel.getUserState()
            .observe(this, Observer { user ->
                when (user.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        handleProgressBar(false)
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        handleProgressBar(false)
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.LOADING -> {
                        handleProgressBar(true)
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        handleProgressBar(false)
                    }
                }
            })
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun handleProgressBar(f: Boolean) {
        val visibility = if (f) View.VISIBLE else View.GONE
        progress_bar.visibility = visibility
    }

    private fun attemptToLogin() {
        if (user_id_input.text.toString().isNotEmpty())
            viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }

    private fun setLogo() {
        glideInstance.load(drawable)
            .into(login_logo)
    }
}
