package com.android254.droidconKE2020.sessions.di

import com.android254.droidconKE2020.sessions.ui.viewmodel.DaySessionsViewModel
import com.android254.droidconKE2020.sessions.ui.viewmodel.SessionDetailViewModel
import com.android254.droidconKE2020.sessions.ui.viewmodel.SessionsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelsModule: Module = module {
    viewModel { DaySessionsViewModel() }
    viewModel { SessionsViewModel(get()) }
    viewModel { SessionDetailViewModel() }
}

val loadModules by lazy {
    loadKoinModules(viewModelsModule)
}