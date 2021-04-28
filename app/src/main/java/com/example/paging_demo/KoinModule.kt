package com.example.paging_demo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val githubModule = module {
    viewModel { GitHubViewModel(get()) }
}

val repoModule = module {
    single { GitHubRepository() }
}