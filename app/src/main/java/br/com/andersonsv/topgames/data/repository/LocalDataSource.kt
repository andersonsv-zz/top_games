package br.com.andersonsv.topgames.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.data.network.GameData
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.vo.BoundaryState
import java.util.Date

interface LocalDataSource {
    fun getGames() : LiveData<PagedList<Game>>
    fun getBoundaryState(): LiveData<BoundaryState>
    fun insertGames(movies: List<GameData>)
    fun deleteGames()
    fun refresh()
    fun getGame(id : Long) : LiveData<Game>
}