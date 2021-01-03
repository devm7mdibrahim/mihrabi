package com.devm7mdibrahim.mihrabi.ui.azkar.mapper

import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.model.network.response.azkar.Content
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AzkarEntityMapper @Inject constructor() {
    fun transformToLocal(azkarList: List<Content>): List<Azkar>{
        return azkarList.map {
            Azkar(
                id = it.id,
                text = it.zekr,
                type = it.type
            )
        }
    }

    fun transformToDomain(azkarList: List<Azkar>): List<Content>{
        return azkarList.map {
            Content(
                id = it.id,
                zekr = it.text,
                type = it.type
            )
        }
    }
}