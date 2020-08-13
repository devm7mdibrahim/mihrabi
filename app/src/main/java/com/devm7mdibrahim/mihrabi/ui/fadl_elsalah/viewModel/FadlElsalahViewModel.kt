package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.repo.FadlElsalahRepository
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FadlElsalahViewModel @ViewModelInject constructor(private val fadlElsalahRepository: FadlElsalahRepository):  ViewModel(){

    private val _fadl = MutableLiveData<DataState<List<Fadl>>>()

    val fadl: LiveData<DataState<List<Fadl>>>
        get() = _fadl

    fun getFadlElsalahList(type: Int) {
        viewModelScope.launch {
            fadlElsalahRepository.fetchFadlElsalahData(type = type)
                    .onEach {
                        _fadl.postValue(it)
                    }.launchIn(viewModelScope)
        }
    }
}