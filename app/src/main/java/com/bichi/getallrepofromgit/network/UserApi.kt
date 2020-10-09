package com.bichi.getallrepofromgit.network

import com.bichi.getallrepofromgit.model.Followers
import com.bichi.getallrepofromgit.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserApi {
    @GET("{username}/repos")
    @Headers("Content-Type: application/json")
    fun fetchRepoList(@Path(value = "username") username: String?): Observable<List<User>>

    @GET("{username}/followers")
    @Headers("Content-Type: application/json")
    fun getFollowers(@Path(value = "username") username: String?): Observable<List<Followers>>

    companion object{
        operator fun invoke():UserApi{
            return RetrofitClient.getInstance()!!.create(UserApi::class.java)
        }
    }
}