package com.animescrap.feature.home.presentation.viewmodel

import com.animescrap.core.helper.Resource
import com.animescrap.data.model.home.domain.NewEpisodeDomain
import com.animescrap.feature.home.repository.HomeRepository
import com.animescrap.util.rule.instantLiveDataAndCoroutineRules
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val repository: HomeRepository = mockkClass(HomeRepository::class)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun givenFetchListNewEpisodeSuccessfully_shouldEmitSuccess() = runBlocking {
        val listNewEpisodeDomain = listOf<NewEpisodeDomain>(mockk())
        coEvery { repository.getListNewEpisode(any()) } returns listNewEpisodeDomain

        viewModel.fetchListNewEpisode()

        assertEquals(viewModel.getLiveDataListNewEpisode.value, Resource.success(listNewEpisodeDomain))
    }

    @Test
    fun givenFetchListNewEpisodeError_shouldEmitError() = runBlocking {
        val error = Throwable()
        coEvery { repository.getListNewEpisode(any()) } throws error

        viewModel.fetchListNewEpisode()

        assertEquals(viewModel.getLiveDataListNewEpisode.value, Resource.error<Throwable>(error))
    }
}