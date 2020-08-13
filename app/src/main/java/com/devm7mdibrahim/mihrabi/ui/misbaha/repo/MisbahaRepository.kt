package com.devm7mdibrahim.mihrabi.ui.misbaha.repo

interface MisbahaRepository {
    fun getMisbahaCount(): Int
    fun setMisbahaCount(count: Int)
    fun isVibrateOn(): Boolean
    fun setVibrateOn(boolean: Boolean)
}