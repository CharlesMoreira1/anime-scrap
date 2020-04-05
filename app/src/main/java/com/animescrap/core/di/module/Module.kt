package com.animescrap.core.di.module

import com.animescrap.data.source.remote.api.ApiServiceSoup
import com.animescrap.feature.catalog.presentation.viewmodel.CatalogViewModel
import com.animescrap.feature.catalog.repository.CatalogRepository
import com.animescrap.feature.home.presentation.viewmodel.HomeViewModel
import com.animescrap.feature.home.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepository(get()) }
    single<CatalogRepository> { CatalogRepository(get()) }
}

val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<CatalogViewModel> { CatalogViewModel(get()) }
}

val apiServiceClientModule = module {
    single<ApiServiceSoup> { ApiServiceSoup }
}