package com.bichi.getallrepofromgit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bichi.getallrepofromgit.repository.UserRepository

class MyViewModelFactory(private var repository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}