package com.example.paging_demo

import io.reactivex.Single

class GitHubRepository {

    private val apiService = AppClientManager.retrofit.create(GithubApi::class.java)

    fun searchUsers(query: String = "google", page: Int = 0, count: Int = 10): Single<UsersSearchResponse> {
        return apiService.searchUsers(query, page, count)
    }
}