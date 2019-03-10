package br.com.andersonsv.topgames.data.network

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "games")
data class GameData (
    @PrimaryKey
    val id: Long = 0,

    val viewers: String,
    val channels: String
)
