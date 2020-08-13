package com.devm7mdibrahim.mihrabi.model.network.response.qibla

data class QiblaData(
        val address: String,
        val city: String,
        val state: String,
        val country: String,
        val postalCode: String,
        val knownName: String,
        val longitude: Double,
        val latitude: Double,
        val altitude: Double
)