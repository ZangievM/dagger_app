package com.zm.daggerapp.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zm.daggerapp.SessionManager
import com.zm.daggerapp.model.Post
import com.zm.daggerapp.network.main.MainApi
import com.zm.daggerapp.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    private val posts = MediatorLiveData<Resource<out List<Post?>>>()

    init {
        posts.value = Resource.loading(listOf(Post.nullable()))
    }

    fun observePosts(): LiveData<Resource<out List<Post?>>> {
        val source =
            LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(
                    sessionManager.getAuthUser()
                        .value!!
                        .data!!.id
                )
                    .doOnError {
                        listOf(Post())
                    }
                    .map { posts ->
                        if (posts.isNotEmpty()) {
                            if (posts[0].id == -1) return@map Resource.error(
                                "Error",
                                listOf(Post.nullable())
                            )
                        }
                        Resource.success(posts)
                    }.subscribeOn(Schedulers.io())

            )

        posts.addSource(source) {
            posts.value = it
            posts.removeSource(source)
        }
        return posts
    }
}
