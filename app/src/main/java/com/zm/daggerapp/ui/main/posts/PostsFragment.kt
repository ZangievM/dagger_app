package com.zm.daggerapp.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.daggerapp.R
import com.zm.daggerapp.ui.main.Resource
import com.zm.daggerapp.util.VerticalSpacingItemDecoration
import com.zm.daggerapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.posts_fragment.*
import javax.inject.Inject


class PostsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(PostsViewModel::class.java)
        initRV()
        subscribeObservers()
    }

    private fun initRV() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(VerticalSpacingItemDecoration(15))
        recycler_view.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts()
            .observe(viewLifecycleOwner, Observer { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> resource.data?.let {
                        adapter.setPosts(it)
                    }
                    Resource.Status.ERROR -> println("Error${resource.message}")
                    Resource.Status.LOADING -> println("LOADING")
                }
            })
    }

}
