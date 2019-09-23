package com.zm.daggerapp.ui.main.posts

import com.zm.daggerapp.model.Post
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zm.daggerapp.R


class PostsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: List<Post?> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<Post?>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.title)

        fun bind(post: Post?) {
            title.text = post!!.title
        }
    }
}