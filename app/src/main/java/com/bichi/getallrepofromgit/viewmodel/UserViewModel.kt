package com.bichi.getallrepofromgit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bichi.getallrepofromgit.model.Followers
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.network.Resource
import com.bichi.getallrepofromgit.repository.UserRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    var userName: String? = null
    val mUsers: MutableLiveData<Resource<List<User>?>> = MutableLiveData()
    val followers: MutableLiveData<Resource<List<Followers>?>> = MutableLiveData()

    var clickedUser: User? = null
    fun fetchUserRepo() {
        Log.d(TAG, "fetchUserRepo: ")
        repository.fetchUserRepo(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { s -> mUsers.postValue(Resource.loading(null)) } //.doAfterTerminate(() -> loadingStatus.setValue(false))
            .subscribe(object : Observer<List<User>>{
                override fun onComplete() {
                    //TODO("Not yet implemented")
                }

                override fun onSubscribe(d: Disposable) {
                    //TODO("Not yet implemented")
                }

                override fun onNext(users: List<User>) {
                    //mUsers.postValue(Resource.success(users))
                    mUsers.value = Resource.success(users)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    //mUsers.postValue(Resource.error(e.message,null))
                    mUsers.value = Resource.error(e.message,null)
                }

            })
    }

    fun getFollowers() {
        Log.d(TAG, "getFollowers: $userName")
        repository.getFollowers(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { followers.postValue(Resource.loading(null)) }
            .subscribe(object : Observer<List<Followers>>{
                override fun onComplete() {
                    //TODO("Not yet implemented")
                }

                override fun onSubscribe(d: Disposable) {
                    //TODO("Not yet implemented")
                }

                override fun onNext(mfollowers: List<Followers>) {
                    followers.value = Resource.success(mfollowers)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    followers.value = Resource.error(e.message, null)
                }

            })
    }

    companion object {
        val TAG = UserViewModel::class.java.simpleName
    }
}