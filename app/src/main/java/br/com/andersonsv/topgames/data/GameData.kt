package br.com.andersonsv.topgames.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "games")
data class GameData (
    @PrimaryKey
    val id: Long = 0,

    val name: String,
    @SerializedName("viewers")
    val viewers: Float = 0.toFloat(),

    @SerializedName("channels")
    val channels: Float = 0.toFloat()
)