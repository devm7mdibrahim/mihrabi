package com.devm7mdibrahim.mihrabi.ui.main.repo

interface MainRepository {
    fun updateUserLatitude(latitude: String)
    fun updateUserLongitude(longitude: String)
    fun updateUserAltitude(altitude: String)
    fun updateUserCity(city: String)
}