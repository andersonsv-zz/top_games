package br.com.andersonsv.topgames.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.andersonsv.topgames.data.network.GameData
import br.com.andersonsv.topgames.domain.entity.Game


@Dao
interface GameDao {

    @Query("SELECT id, viewers, channels FROM games ")
    fun getDataSourcefactory(): DataSource.Factory<Int, Game>

    @Query("SELECT * FROM games WHERE id= :id")
    fun getGame(id: Long) : LiveData<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(games: List<GameData>)

    @Query("DELETE FROM games")
    fun deleteGames()
}