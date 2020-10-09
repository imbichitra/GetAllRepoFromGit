package com.bichi.getallrepofromgit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bichi.getallrepofromgit.R
import com.bichi.getallrepofromgit.databinding.FragmentUserBinding
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.network.UserApi
import com.bichi.getallrepofromgit.repository.UserRepository
import com.bichi.getallrepofromgit.viewmodel.MyViewModelFactory
import com.bichi.getallrepofromgit.viewmodel.UserViewModel


class UserFragment : Fragment() ,MyInterface {
    var userViewModel: UserViewModel? = null
    lateinit var binding: FragmentUserBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        //return inflater.inflate(R.layout.fragment_user, container, false);
        //return inflater.inflate(R.layout.fragment_user, container, false);
        val api = UserApi()
        val repository = UserRepository(api)
        val factory = MyViewModelFactory(repository)
        userViewModel = activity?.let { ViewModelProvider(it, factory) }?.get(UserViewModel::class.java)

        binding.setViewModel(userViewModel)
        binding.setMyinteface(this)
        //observeResponse();
        //observeResponse();
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    companion object {
        val TAG = UserFragment::class.java.simpleName
    }

    override fun onSubmitClick() {
        if (userViewModel!!.userName != null && !userViewModel!!.userName!!.isEmpty()) {
            navController!!.navigate(R.id.action_userFragment_to_userLisFragment)
        } else {
            Toast.makeText(context, "Enter username", Toast.LENGTH_SHORT).show()
        }
    }

    override fun userClicked(user: User?) {
        //TODO("Not yet implemented")
    }
}