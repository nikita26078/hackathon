package ru.otkrytie.startinvest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(val avatar: String,
                   val author: String,
                   var body: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
