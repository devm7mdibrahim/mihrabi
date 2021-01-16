package com.devm7mdibrahim.mihrabi.ui.azkar.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.ui.azkar.repo.AzkarRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AzkarViewModel @ViewModelInject constructor(private val azkarRepository: AzkarRepository) :
    ViewModel() {

    private val _azkar = MutableLiveData<DataState<List<Azkar>>>()

    val azkar: LiveData<DataState<List<Azkar>>>
        get() = _azkar

    fun fetchAzkar(type: Int) {
        viewModelScope.launch {
            azkarRepository
                .fetchAzkar(type = type, list = emptyList())
                .onEach {
                    _azkar.postValue(it)
                }.launchIn(viewModelScope)
        }
    }
}