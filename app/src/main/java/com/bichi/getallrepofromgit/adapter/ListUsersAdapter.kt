package com.bichi.getallrepofromgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bichi.getallrepofromgit.databinding.ItemUserBinding
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.view.MyInterface
import com.bichi.getallrepofromgit.view.UserLisFragment

class ListUsersAdapter(
    private val context: UserLisFragment,
    private val users: List<User>?
) :
    RecyclerView.Adapter<ListUsersAdapter.UserHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(context.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserHolder,
        position: Int
    ) {
        val user: User = users!![position]
        holder.itemViewBinding.setUser(user)
        holder.itemViewBinding.setListner(context as MyInterface)
        holder.itemViewBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    class UserHolder(var itemViewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
}
