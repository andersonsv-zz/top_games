package br.com.andersonsv.topgames.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.andersonsv.topgames.data.GameData


@Database(
    entities = [
        GameData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GameDb : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDb? = null

        fun getInstance(context: Context): GameDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GameDb::class.java, "game.db"
            ).build()
    }
}