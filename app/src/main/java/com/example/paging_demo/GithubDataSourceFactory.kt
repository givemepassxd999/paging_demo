package com.example.paging_demo

import androidx.paging.DataSource

class GithubDataSourceFactory(
    private val searchQuery: String
) : DataSource.Factory<Int, Item>() {

    override fun create(): DataSource<Int, Item> {
        return GithubPageKeyedDataSource(searchQuery)
    }
}