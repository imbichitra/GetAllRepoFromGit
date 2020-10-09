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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bichi.getallrepofromgit.R
import com.bichi.getallrepofromgit.adapter.ListUsersAdapter
import com.bichi.getallrepofromgit.databinding.FragmentUserLisBinding
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.network.Resource
import com.bichi.getallrepofromgit.network.Resource.Status.*
import com.bichi.getallrepofromgit.network.UserApi
import com.bichi.getallrepofromgit.repository.UserRepository
import com.bichi.getallrepofromgit.viewmodel.MyViewModelFactory
import com.bichi.getallrepofromgit.viewmodel.UserViewModel

class UserLisFragment : Fragment(),MyInterface {

    val TAG = UserLisFragment::class.java.simpleName
    var userViewModel: UserViewModel? = null
    lateinit var binding: FragmentUserLisBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_lis, container, false)
        val api = UserApi()
        val repository = UserRepository(api)
        val factory = MyViewModelFactory(repository)
        userViewModel = ViewModelProvider(requireActivity(), factory)[UserViewModel::class.java]
        Log.d(TAG, "onCreate:UserLisFragment " + userViewModel!!.userName)
        observeResponse()

        userViewModel!!.fetchUserRepo()
        return binding.root
    }

    fun observeResponse() {
        userViewModel!!.mUsers.observe(viewLifecycleOwner,
            Observer<Resource<List<User>?>> { listResource ->
                if (listResource != null) {
                    when (listResource.status) {
                        LOADING -> {
                            Log.d(TAG, "onChanged: UserLisFragment: LOADING...")
                        }
                        SUCCESS -> {
                            initAdapter(listResource.data!!)
                        }
                        ERROR -> {
                            Log.d(
                                TAG,
                                "onChanged: UserLisFragment: ERROR... " + listResource.message
                            )
                        }
                    }
                }
            })
    }
    private fun initAdapter(data: List<User>) {
        Log.d(TAG, "initAdapter: "+data.size)
        binding.listUser.layoutManager = LinearLayoutManager(context)
        val adapter = ListUsersAdapter(this, data)
        binding.listUser.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.listUser.adapter = adapter
    }
    companion object {
        val TAG = UserLisFragment::class.java
    }

    override fun onSubmitClick() {
        //TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    override fun userClicked(user: User?) {
        userViewModel!!.clickedUser = user
        navController!!.navigate(R.id.action_userLisFragment_to_userDetailFragment)

    }
}