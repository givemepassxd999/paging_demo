package com.example.paging_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val gitHubViewModel by viewModel<GitHubViewModel>()
    private val adapter = GithubAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        gitHubViewModel.run {
            loading.observe(this@MainActivity, Observer {
                if (swipe_refresh.isRefreshing.not().and(it)) {
                    progress_bar.visibility = View.VISIBLE
                } else {
                    progress_bar.visibility = View.GONE
                }
            })
            getSearchResult().observe(this@MainActivity, Observer {
                if (swipe_refresh.isRefreshing) {
                    swipe_refresh.isRefreshing = false
                }
                adapter.setData(it)
            })
        }
        search_btn.setOnClickListener {
            refreshData()
        }
        swipe_refresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        val inputStr = search_input.text.toString()
        if (inputStr.isNotEmpty()) {
            adapter.setData(arrayListOf())
            gitHubViewModel.searchUsers(inputStr, 0, 50)
        } else {
            Toast.makeText(this@MainActivity, "請輸入搜尋文字...", Toast.LENGTH_SHORT).show()
        }
    }
}