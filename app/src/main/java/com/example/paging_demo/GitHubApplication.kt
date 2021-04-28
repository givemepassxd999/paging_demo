package com.example.paging_demo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@GitHubApplication)
            // modules
            val list = listOf(githubModule, repoModule)
            modules(list)
        }
    }
}