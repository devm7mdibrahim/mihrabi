package com.devm7mdibrahim.mihrabi.ui.qibla.repo

interface QiblaRepository {
    fun getUserLatitude(): Double
    fun getUserLongitude(): Double
    fun getUserAltitude(): Double
    fun getKaabaLatitude(): Double
    fun getKaabaLongitude(): Double
    fun getKaabaAltitude(): Double
}