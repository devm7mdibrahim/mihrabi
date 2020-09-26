package com.devm7mdibrahim.mihrabi.model.local.fiqh

import javax.inject.Inject

class FiqhLocalDataSourceImpl @Inject constructor(private val fiqhDao: FiqhDao) :
    FiqhLocalDataSource {
    override suspend fun getFiqhData(type: Int): List<Fiqh> = fiqhDao.getFiqhData(type)
    override suspend fun insertFiqhData(fiqhDataList: List<Fiqh>) = fiqhDao.insertFiqhData(fiqhDataList)
}