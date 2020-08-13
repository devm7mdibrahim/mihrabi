package com.devm7mdibrahim.mihrabi.ui.fiqh.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh
import com.devm7mdibrahim.mihrabi.ui.fiqh.repo.FiqhRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FiqhViewModel @ViewModelInject constructor(private val fiqhRepository: FiqhRepository) :
    ViewModel() {

    private val _fiqh = MutableLiveData<DataState<List<Fiqh>>>()

    val fiqh: LiveData<DataState<List<Fiqh>>>
        get() = _fiqh

    fun fetchData(type: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            fiqhRepository.fetchFiqhData(type = type)
                .onEach {
                    _fiqh.postValue(it)
                }.launchIn(viewModelScope)
        }
    }
}