package ru.otkrytie.startinvest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Investment(
    val category: Int,
    val title: String,
    val desc: String
)  {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
