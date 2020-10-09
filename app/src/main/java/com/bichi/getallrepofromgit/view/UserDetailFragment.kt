package com.bichi.getallrepofromgit.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bichi.getallrepofromgit.R
import com.bichi.getallrepofromgit.databinding.FragmentUserDetailBinding
import com.bichi.getallrepofromgit.model.Followers
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.network.Resource
import com.bichi.getallrepofromgit.network.UserApi
import com.bichi.getallrepofromgit.repository.UserRepository
import com.bichi.getallrepofromgit.viewmodel.MyViewModelFactory
import com.bichi.getallrepofromgit.viewmodel.UserViewModel
import com.bichi.getallrepofromgit.network.Resource.Status.*

class UserDetailFragment : Fragment() {
    val TAG = UserDetailFragment::class.java.simpleName
    lateinit var binding: FragmentUserDetailBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        val api = UserApi()
        val repository = UserRepository(api)
        val factory = MyViewModelFactory(repository)
        userViewModel = ViewModelProvider(requireActivity(), factory)[UserViewModel::class.java]
        binding.user = userViewModel.clickedUser
        observeFollowers()
        userViewModel.getFollowers()
        return binding.root
    }

    private fun observeFollowers(){
        userViewModel.followers.observe(
            viewLifecycleOwner,
            Observer<Resource<List<Followers>?>> { listResource ->
                if (listResource != null) {
                    when (listResource.status) {
                        LOADING -> {
                            Log.d(
                                TAG,
                                "onChanged: UserDetailFragment: LOADING..."
                            )
                        }
                        SUCCESS -> {
                            Log.d(TAG, "observeFollowers: "+ (listResource.data!![0].avatarUrl ))
                            binding.followers = listResource.data
                        }
                        ERROR -> {
                            Log.d(
                                TAG,
                                "onChanged: UserDetailFragment: ERROR... " + listResource.message
                            )
                        }
                    }
                }
            })
    }
    companion object {
       val TAG = UserDetailFragment::class.java
    }
}