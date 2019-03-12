package br.com.andersonsv.topgames.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.AppExecutors
import br.com.andersonsv.topgames.data.GameData
import br.com.andersonsv.topgames.data.repository.LocalDataSource
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.vo.BoundaryState


class LocalData(private val appExecutors: AppExecutors,
                private val gameDao: GameDao) :
    LocalDataSource {

    private val boundaryCallback =
        GameBoundaryCallback()

    override fun getGames(): LiveData<PagedList<Game>> {
        val dataSourceFactory = gameDao.getDataSourcefactory()
        return LivePagedListBuilder(dataSourceFactory, GameBoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    override fun getGame(id : Long): LiveData<Game> {
        return gameDao.getGame(id)
    }

    override fun getBoundaryState(): LiveData<BoundaryState> {
        return boundaryCallback.boundaryState
    }

    override fun insertGames(games: List<GameData>) {
        appExecutors.diskIO().execute {
            gameDao.insertGames(games)
        }
    }

    override fun deleteGames() {
        gameDao.deleteGames()
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }
}