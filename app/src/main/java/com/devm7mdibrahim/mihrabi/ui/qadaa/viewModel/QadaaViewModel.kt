package com.devm7mdibrahim.mihrabi.ui.qadaa.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.qadaa.repo.QadaaRepository

class QadaaViewModel @ViewModelInject constructor(private val qadaaRepository: QadaaRepository): ViewModel() {

    fun getQadaaData(type: Int) = qadaaRepository.getQadaaData(type)
    fun setQadaaData(type: Int, number: Int) = qadaaRepository.setQadaaData(type, number)
}