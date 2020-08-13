package com.devm7mdibrahim.mihrabi.model.local.fadl_salah

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fadl")
data class Fadl(@PrimaryKey val id: Int, val text: String, val type: Int)