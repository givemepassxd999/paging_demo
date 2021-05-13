package com.example.paging_demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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
        with(gitHubViewModel) {
            items.observe(this@MainActivity, Observer {
                adapter.submitList(it)
            })
        }
        search_btn.setOnClickListener {
            refreshData()
        }
    }

    private fun refreshData() {
        val inputStr = search_input.text.toString()
        if (inputStr.isNotEmpty()) {
            gitHubViewModel.searchUsers(inputStr)
        } else {
            Toast.makeText(this@MainActivity, "請輸入搜尋文字...", Toast.LENGTH_SHORT).show()
        }
    }
}