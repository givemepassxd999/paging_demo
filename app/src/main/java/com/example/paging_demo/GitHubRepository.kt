package com.example.paging_demo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors

class GitHubRepository {

    fun searchUsers(
        query: String = "google",
        pageSize: Int = 10
    ): LiveData<PagedList<Item>> {
        val factory = GithubDataSourceFactory(query)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPageSize(pageSize)
            .build()

        return LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }
}