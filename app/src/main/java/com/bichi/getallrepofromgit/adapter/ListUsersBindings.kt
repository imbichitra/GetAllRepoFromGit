package com.bichi.getallrepofromgit.adapter

import android.content.ContentValues
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bichi.getallrepofromgit.model.Followers
import com.bumptech.glide.Glide

@BindingAdapter("dataList")
fun loadUsers(
    recyclerView: RecyclerView,
    users: List<Followers?>?
) {
    if (users != null && users.isEmpty()) return
    val layoutManager = recyclerView.layoutManager
    if (layoutManager == null) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
    Log.d(ContentValues.TAG, "loadUsers: ")
    val adapter = FollowersAdapter(users)
    recyclerView.addItemDecoration(
        DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
    )
    recyclerView.adapter = adapter
}

@BindingAdapter("avatar")
fun loadUserAvatar(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}