package com.devm7mdibrahim.mihrabi.model.local.fiqh

import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh

interface FiqhLocalDataSource {
    suspend fun getFiqhData(type: Int): List<Fiqh>
    suspend fun insertFiqhData(fiqhDataList: List<Fiqh>)
}