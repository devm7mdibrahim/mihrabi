package com.devm7mdibrahim.mihrabi.ui.imaniat.repo

interface ImaniatRepository {
    fun getImaniatFirstTime(): Boolean
    fun setImaniatFirstTime(boolean: Boolean)
}