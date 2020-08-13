package com.devm7mdibrahim.mihrabi.ui.quran.quran.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter.Page
import com.devm7mdibrahim.mihrabi.ui.quran.quran.repo.QuranRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class QuranViewModel @ViewModelInject constructor(private val quranRepository: QuranRepository) : ViewModel() {

    private val _quranPages = MutableLiveData<DataState<List<Page>>>()

    init {
        fetchPagesList()
    }

    private fun fetchPagesList() {
        viewModelScope.launch(Dispatchers.IO) {
            quranRepository.getQuranPagesList()
                    .onEach {
                        _quranPages.postValue(it)
                    }.launchIn(viewModelScope)
        }
    }

    val quranPages: LiveData<DataState<List<Page>>>
        get() = _quranPages
}