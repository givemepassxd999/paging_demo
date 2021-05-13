package com.example.paging_demo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel

class GitHubViewModel(private val repository: GitHubRepository) : ViewModel() {
    private val searchQuery = MutableLiveData<String>()
    private val searchResult = map(searchQuery) {
        repository.searchUsers(it, 10)
    }
    val items = switchMap(searchResult) { it }

    @SuppressLint("CheckResult")
    fun searchUsers(query: String) {
        Log.d("GitHubViewModel", "searchQuery.value:${searchQuery.value}")
        Log.d("GitHubViewModel", "query:$query")
        if (searchQuery.value != query) {
            this.searchQuery.value = query
        }
    }
}