package com.animescrap.core.di.module

import com.animescrap.data.source.local.AppDatabase
import com.animescrap.data.source.remote.api.ApiServiceSoup
import com.animescrap.feature.catalog.presentation.viewmodel.CatalogViewModel
import com.animescrap.feature.catalog.repository.CatalogRepository
import com.animescrap.feature.detail.presentation.viewmodel.DetailViewModel
import com.animescrap.feature.detail.repository.DetailRepository
import com.animescrap.feature.episodedownload.presentation.ui.viewmodel.EpisodeDownloadViewModel
import com.animescrap.feature.history.presentation.viewmodel.HistoryViewModel
import com.animescrap.feature.history.repository.HistoryRepository
import com.animescrap.feature.home.presentation.viewmodel.HomeViewModel
import com.animescrap.feature.home.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
    single { CatalogRepository(get(), get()) }
    single { HistoryRepository(get()) }
    single { DetailRepository(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CatalogViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { EpisodeDownloadViewModel(get()) }
}

val apiServiceClientModule = module {
    single { ApiServiceSoup }
}

val databaseModule = module {
    single { AppDatabase.getInstance(context = get()) }
    single { get<AppDatabase>().catalogDao() }
    single { get<AppDatabase>().historyDao() }

}