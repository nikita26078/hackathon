package ru.otkrytie.startinvest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subscription(val avatar: String,
                        val name: String)  {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
