package com.example.paging_demo

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubPageKeyedDataSource(
    private val searchQuery: String,
) : PageKeyedDataSource<Int, Item>() {

    private val apiService = AppClientManager.retrofit.create(GithubApi::class.java)

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        val currentPage = 1
        apiService.searchUsers(searchQuery, currentPage, 10)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                val items: List<Item> = res.items ?: arrayListOf()
                callback.onResult(items, null, currentPage)
            }, {

            })

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Item>
    ) {
        val currentPage = params.key
        val nextPage = currentPage + 1
        apiService.searchUsers(searchQuery, currentPage, 10)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                val items: List<Item> = res.items ?: arrayListOf()
                callback.onResult(items, nextPage)
            }, {

            })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Item>
    ) {

    }

}