package com.devm7mdibrahim.mihrabi.model.local.azkar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "azkar")
data class Azkar(@PrimaryKey val id: Int, val text: String, val type: Int)