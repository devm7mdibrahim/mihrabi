package com.devm7mdibrahim.mihrabi.ui.imaniat.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.imaniat.repo.ImaniatRepository

class ImaniatViewModel
@ViewModelInject constructor(private val imaniatRepository: ImaniatRepository) :
    ViewModel() {

    fun getImaniatFirstTime(): Boolean = imaniatRepository.getImaniatFirstTime()
    fun setImaniatFirstTime(boolean: Boolean) = imaniatRepository.setImaniatFirstTime(boolean)
}