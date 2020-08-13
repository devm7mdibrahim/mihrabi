package com.devm7mdibrahim.mihrabi.ui.misbaha.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.misbaha.repo.MisbahaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MisbahaViewModel @ViewModelInject constructor(private val misbahaRepository: MisbahaRepository) : ViewModel() {

    fun getMisbahaCount(): Int = misbahaRepository.getMisbahaCount()
    fun setMisbahaCount(count: Int) = misbahaRepository.setMisbahaCount(count)

    fun setVibrateOn(boolean: Boolean) = misbahaRepository.setVibrateOn(boolean)
    fun isVibrateOn(): Boolean = misbahaRepository.isVibrateOn()
}