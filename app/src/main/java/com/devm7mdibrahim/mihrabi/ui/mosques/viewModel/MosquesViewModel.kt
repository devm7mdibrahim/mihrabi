package com.devm7mdibrahim.mihrabi.ui.mosques.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.Result
import com.devm7mdibrahim.mihrabi.ui.mosques.repo.MosquesRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MosquesViewModel @ViewModelInject constructor(private val mosquesRepository: MosquesRepository) : ViewModel() {
    private val mosques = MutableLiveData<DataState<List<Result>>>()

    val nearByMosques: LiveData<DataState<List<Result>>>
        get() = mosques

    fun fetchNearbyMosques(location: String) {
        viewModelScope.launch {
            mosquesRepository.getNearbyMosques(location)
                    .onEach {
                        mosques.postValue(it)
                    }.launchIn(viewModelScope)
        }
    }

    fun getLocation(): String = mosquesRepository.getLocation()
}