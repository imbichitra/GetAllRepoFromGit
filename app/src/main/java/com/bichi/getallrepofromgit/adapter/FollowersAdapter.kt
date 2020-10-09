package com.bichi.getallrepofromgit.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bichi.getallrepofromgit.BR
import com.bichi.getallrepofromgit.databinding.FollowersItemBinding
import com.bichi.getallrepofromgit.model.Followers

class FollowersAdapter(
    private val followers: List<Followers?>?
) :
    RecyclerView.Adapter<FollowersAdapter.GenericViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = FollowersItemBinding.inflate(layoutInflater, parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GenericViewHolder,
        position: Int
    ) {
        val mfollowers = followers!![position]
        holder.itemViewBind.setVariable(BR.follower,mfollowers)
    }

    override fun getItemCount(): Int {
        return followers?.size ?: 0
    }

    class GenericViewHolder(var itemViewBind: ViewDataBinding) :
        RecyclerView.ViewHolder(itemViewBind.root)

    companion object {
        val TAG = FollowersAdapter::class.java.simpleName
    }
}