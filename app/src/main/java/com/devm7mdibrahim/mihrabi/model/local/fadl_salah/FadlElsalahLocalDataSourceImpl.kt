package com.devm7mdibrahim.mihrabi.model.local.fadl_salah

import javax.inject.Inject

class FadlElsalahLocalDataSourceImpl @Inject constructor(private val fadlElsalahDao: FadlElsalahDao) :
    FadlElsalahLocalDataSource {
    override suspend fun getFadlElsalahData(type: Int): List<Fadl> = fadlElsalahDao.getFadlElsalahData(type)
    override suspend fun insertFadlElsalahData(fadlElsalahList: List<Fadl>) = fadlElsalahDao.insertFadlElsalahData(fadlElsalahList)
}