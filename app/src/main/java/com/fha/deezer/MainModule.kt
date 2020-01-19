package com.fha.deezer

import com.fha.deezer.service.repository.DeezerService
import com.fha.deezer.service.repository.PlaylistListRepository
import com.fha.deezer.service.repository.PlaylistRepository
import com.fha.deezer.utils.BASE_URL
import com.fha.deezer.viewmodel.PlaylistListViewModel
import com.fha.deezer.viewmodel.PlaylistViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    single { PlaylistListRepository(get()) }
    single { PlaylistRepository(get()) }

    single { createWebService() }

    viewModel { PlaylistListViewModel(get()) }
    viewModel { PlaylistViewModel(get()) }
}

fun createWebService(): DeezerService {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    return retrofit.create(DeezerService::class.java)
}