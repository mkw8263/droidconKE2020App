package com.android254.droidconKE2020.home.di

import com.android254.droidconKE2020.home.repositories.FakeSpeakerRepository
import com.android254.droidconKE2020.home.ui.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    single { FakePromotionRepository() }
    single { FakeOrganizerRepository() }
    single { FakeSpeakerRepository() }
}