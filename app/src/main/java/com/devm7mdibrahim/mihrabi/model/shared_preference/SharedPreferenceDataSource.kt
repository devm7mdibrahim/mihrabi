package com.devm7mdibrahim.mihrabi.model.shared_preference

interface SharedPreferenceDataSource {
    fun setUserLatitude(lat: String)
    fun setUserLongitude(long: String)
    fun setUserAltitude(altitude: String)
    fun setUserCountry(county: String)

    fun getUserLatitude(): String
    fun getUserLongitude(): String
    fun getUserAltitude(): String
    fun getUserCountry(): String


    fun getKaabaLatitude(): String
    fun getKaabaLongitude(): String
    fun getKaabaAltitude(): String

    fun getMisbahaCount(): Int
    fun setMisbahaCount(count: Int)

    fun isVibrationOn(): Boolean
    fun setVibrationOn(boolean: Boolean)

    fun getQadaaData(type: Int): Int
    fun setQadaaData(type: Int, number: Int)
}