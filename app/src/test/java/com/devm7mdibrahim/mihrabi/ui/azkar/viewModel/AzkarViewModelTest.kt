package com.devm7mdibrahim.mihrabi.ui.azkar.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devm7mdibrahim.mihrabi.MainCoroutineRule
import com.devm7mdibrahim.mihrabi.getOrAwaitValue
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.ui.azkar.repo.FakeAzkarRepositoryImpl
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AzkarViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var azkarViewModel: AzkarViewModel

    @Before
    fun setUp() {
        azkarViewModel = AzkarViewModel(FakeAzkarRepositoryImpl())
    }

    @Test
    fun `fetchAzkar() when database is not empty then return data from local database`() {
        //arrange
        val list = listOf(Azkar(1, "sdfsdf", 1), Azkar(2, "asds", 1))
        val type = 1

        //act
        azkarViewModel.fetchAzkar(type)

        //assert
        val value = azkarViewModel.azkar.getOrAwaitValue()

        val data = mutableListOf<Azkar>()
        if (value is DataState.Success) {
            data.addAll(value.data)
        }

        assertThat(data).isNotEmpty()
    }

    @Test
    fun `fetchAzkar() when database is empty then return data from request data from server`() {
        //arrange
        val list = emptyList<Azkar>()
        val type = 1

        //act
        azkarViewModel.fetchAzkar(type)

        //assert
        val value = azkarViewModel.azkar.getOrAwaitValue()

        val data = mutableListOf<Azkar>()
        if (value is DataState.Success) {
            data.addAll(value.data)
        }

        assertThat(data).isNotEmpty()
    }
}