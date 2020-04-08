package com.animescrap.core.di.module

import com.animescrap.data.source.local.AppDatabase
import com.animescrap.data.source.local.dao.CatalogDao
import com.animescrap.data.source.remote.api.ApiServiceSoup
import com.animescrap.feature.catalog.presentation.viewmodel.CatalogViewModel
import com.animescrap.feature.catalog.repository.CatalogRepository
import com.animescrap.feature.detail.presentation.viewmodel.DetailViewModel
import com.animescrap.feature.detail.repository.DetailRepository
import com.animescrap.feature.home.presentation.viewmodel.HomeViewModel
import com.animescrap.feature.home.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepository(get()) }
    single<CatalogRepository> { CatalogRepository(get(), get()) }
    single<DetailRepository> { DetailRepository(get()) }
}

val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<CatalogViewModel> { CatalogViewModel(get()) }
    viewModel<DetailViewModel> { DetailViewModel(get()) }
}

val apiServiceClientModule = module {
    single<ApiServiceSoup> { ApiServiceSoup }
}

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getInstance(context = get()) }
    single<CatalogDao> { get<AppDatabase>().catalogDao() }
}