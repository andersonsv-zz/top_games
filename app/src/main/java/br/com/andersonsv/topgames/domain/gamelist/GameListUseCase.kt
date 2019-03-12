package br.com.andersonsv.topgames.domain.gamelist

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.vo.BoundaryState
import br.com.andersonsv.topgames.domain.vo.Direction
import br.com.andersonsv.topgames.domain.vo.LoadingStatus
import java.util.logging.Logger

class GameListUseCase(private val repository: GameListRepository){
    fun getGames(): LiveData<PagedList<Game>> {
        return repository.getGames()
    }

    fun getBoundaryState(): LiveData<BoundaryState> {
        return repository.getBoundaryState()
    }

    fun fetchMore(direction: Direction) : LiveData<LoadingStatus> {
       // val offset = when (direction) {
            //Direction.BOTTOM -> itemDate.addDays(-1)
       //     Direction.TOP -> +1
       // }
        return repository.fetchMore()
    }

    fun refresh(){
        repository.refresh()
    }
}