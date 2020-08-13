package com.devm7mdibrahim.mihrabi.ui.quran.surahList.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.ui.quran.surahList.repo.SurahListRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SurahListViewModel @ViewModelInject constructor(private val surahListRepository: SurahListRepository) : ViewModel() {

    private val _surahListResponse = MutableLiveData<DataState<List<Surah>>>()

    init {
        fetchSurahData()
    }

    private fun fetchSurahData() {
        viewModelScope.launch(Dispatchers.IO) {
            surahListRepository.getSurahList()
                    .onEach {
                       _surahListResponse.postValue(it)
                    }.launchIn(viewModelScope)
        }
    }

    val surah: LiveData<DataState<List<Surah>>>
        get() = _surahListResponse

}