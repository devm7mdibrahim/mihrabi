package com.devm7mdibrahim.mihrabi.model.local.fiqh

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiqh")
data class Fiqh(@PrimaryKey val id: Int, val question: String, val answer: String, val type: Int)