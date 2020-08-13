package com.devm7mdibrahim.mihrabi.ui.qadaa.repo

interface QadaaRepository {
    fun getQadaaData(type: Int): Int
    fun setQadaaData(type: Int, number: Int)
}