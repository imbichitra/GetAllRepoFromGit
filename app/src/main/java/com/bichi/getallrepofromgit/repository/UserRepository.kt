package com.bichi.getallrepofromgit.repository

import com.bichi.getallrepofromgit.model.Followers
import com.bichi.getallrepofromgit.model.User
import com.bichi.getallrepofromgit.network.UserApi
import io.reactivex.Observable

class UserRepository(private val api: UserApi) {
    fun fetchUserRepo(userName: String?): Observable<List<User>> {
        return api.fetchRepoList(userName)
    }

    fun getFollowers(userName: String?): Observable<List<Followers>> {
        return api.getFollowers(userName)
    }

}