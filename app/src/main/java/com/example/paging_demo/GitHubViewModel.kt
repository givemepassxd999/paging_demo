package com.example.paging_demo

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitHubViewModel(private val repository: GitHubRepository) : ViewModel() {
    private var searchResult = MutableLiveData<List<String>>()
    val loading = MutableLiveData<Boolean>()

    fun getSearchResult(): LiveData<List<String>> = searchResult

    @SuppressLint("CheckResult")
    fun searchUsers(query: String, page: Int, count: Int) {

        repository.searchUsers(query, page, count)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(toggleLoading())
            .subscribe({ res ->
                val list = res.items?.map { item ->
                    item.login ?: ""
                }
                searchResult.value = list
            }, {
                loading.value = false
            })
    }

    private fun <T> toggleLoading(): SingleTransformer<T, T> {
        return SingleTransformer { single ->
            single
                .doOnSubscribe { loading.value = true }
                .doFinally { loading.value = false }
        }
    }

}