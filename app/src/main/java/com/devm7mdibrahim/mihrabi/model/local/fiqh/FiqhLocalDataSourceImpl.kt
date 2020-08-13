package com.devm7mdibrahim.mihrabi.model.local.fiqh

import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhDao
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhLocalDataSource
import javax.inject.Inject

class FiqhLocalDataSourceImpl @Inject constructor(private val fiqhDao: FiqhDao) :
    FiqhLocalDataSource {
    override suspend fun getFiqhData(type: Int): List<Fiqh> = fiqhDao.getFiqhData(type)
    override suspend fun insertFiqhData(fiqhDataList: List<Fiqh>) = fiqhDao.insertFiqhData(fiqhDataList)
}