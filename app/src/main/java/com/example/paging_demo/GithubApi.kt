package com.example.paging_demo

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    /**
     * Get repos ordered by stars.
     */
    @GET("search/users?sort=followers")
    fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<UsersSearchResponse>
}