package com.zm.daggerapp.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zm.daggerapp.R
import com.zm.daggerapp.model.User
import com.zm.daggerapp.ui.auth.AuthResource
import com.zm.daggerapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    private val TAG = "ProfileFragment"
    @Inject
    lateinit var factory: ViewModelProviderFactory


    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    @Suppress("NON_EXHAUSTIVE_WHEN")
    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer { authResource ->
            when(authResource.status){
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    setUserDetail(authResource.data)
                }
                AuthResource.AuthStatus.ERROR -> {
                    setErrorDetails(authResource.message)
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        message?.let {
            email.text = message
            website.text = "error"
            username.text = "error"
        }
    }

    private fun setUserDetail(data: User?) {
        data?.let {
            email.text = it.email
            website.text = it.website
            username.text = it.username
        }
    }
}
