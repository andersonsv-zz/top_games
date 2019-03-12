package br.com.andersonsv.topgames.domain.gamelist

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.vo.BoundaryState
import br.com.andersonsv.topgames.domain.vo.LoadingStatus

interface GameListRepository {
    fun getGames(): LiveData<PagedList<Game>>
    fun getBoundaryState(): LiveData<BoundaryState>
    fun fetchMore() : LiveData<LoadingStatus>
    fun returnLoadingOrSuccess() : LiveData<LoadingStatus>
    fun refresh()
}