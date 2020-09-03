package com.devm7mdibrahim.mihrabi.model.shared_preference

import android.content.SharedPreferences
import com.devm7mdibrahim.mihrabi.utils.Constants.IMANIAT_FIRST
import com.devm7mdibrahim.mihrabi.utils.Constants.KAABA_ALTITUDE
import com.devm7mdibrahim.mihrabi.utils.Constants.KAABA_LATITUDE
import com.devm7mdibrahim.mihrabi.utils.Constants.KAABA_LONGITUDE
import com.devm7mdibrahim.mihrabi.utils.Constants.MISBAHA_COUNT
import com.devm7mdibrahim.mihrabi.utils.Constants.QADAA_COUNT
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_ALTITUDE
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_COUNTRY
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_LAT
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_LONG
import com.devm7mdibrahim.mihrabi.utils.Constants.VIBRATION_ON
import javax.inject.Inject

class SharedPreferenceDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedPreferenceDataSource {

    //user latitude
    override fun setUserLatitude(lat: String) =
        sharedPreferences.edit().putString(USER_LAT, lat).apply()

    override fun getUserLatitude(): String =
        sharedPreferences.getString(USER_LAT, "30.0444").toString()


    //user longitude
    override fun setUserLongitude(long: String) =
        sharedPreferences.edit().putString(USER_LONG, long).apply()

    override fun getUserLongitude(): String =
        sharedPreferences.getString(USER_LONG, "31.2357").toString()


    //user altitude
    override fun setUserAltitude(altitude: String) =
        sharedPreferences.edit().putString(USER_ALTITUDE, altitude).apply()

    override fun getUserAltitude(): String =
        sharedPreferences.getString(USER_ALTITUDE, "23").toString()


    //user country
    override fun setUserCountry(county: String) =
        sharedPreferences.edit().putString(USER_COUNTRY, county).apply()

    override fun getUserCountry(): String =
        sharedPreferences.getString(USER_COUNTRY, "القاهرة، مصر").toString()


    //misbaha count
    override fun setMisbahaCount(count: Int) =
        sharedPreferences.edit().putInt(MISBAHA_COUNT, count).apply()

    override fun getMisbahaCount(): Int =
        sharedPreferences.getInt(MISBAHA_COUNT, 0)


    //vibration on misbaha
    override fun setVibrationOn(boolean: Boolean) =
        sharedPreferences.edit().putBoolean(VIBRATION_ON, boolean).apply()

    override fun isVibrationOn(): Boolean =
        sharedPreferences.getBoolean(VIBRATION_ON, true)


    //qadaa data
    override fun setQadaaData(type: Int, number: Int) =
        sharedPreferences.edit().putInt((QADAA_COUNT + type), number).apply()

    override fun getQadaaData(type: Int): Int =
        sharedPreferences.getInt((QADAA_COUNT + type), 0)


    //imaniat first time
    override fun setIsFirstTimeInImaniat(boolean: Boolean) =
        sharedPreferences.edit().putBoolean(IMANIAT_FIRST, boolean).apply()

    override fun isFirstTimeInImaniat(): Boolean =
        sharedPreferences.getBoolean(IMANIAT_FIRST, true)


    //kaaba data
    override fun getKaabaLatitude(): String =
        sharedPreferences.getString(KAABA_LATITUDE, "21.422487").toString()

    override fun getKaabaLongitude(): String =
        sharedPreferences.getString(KAABA_LONGITUDE, "39.826206").toString()

    override fun getKaabaAltitude(): String =
        sharedPreferences.getString(KAABA_ALTITUDE, "277.0").toString()
}