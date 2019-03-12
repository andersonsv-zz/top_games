package br.com.andersonsv.topgames.data.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.vo.BoundaryState
import timber.log.Timber
import java.util.Date


class GameBoundaryCallback : PagedList.BoundaryCallback<Game>() {

    private val _boundaryState = MutableLiveData<BoundaryState>()
    val boundaryState : LiveData<BoundaryState>
        get() = _boundaryState

    companion object {
        const val DATABASE_PAGE_SIZE = 15
    }

    override fun onItemAtFrontLoaded(itemAtFront: Game) {
       BoundaryState.itemLoadedAtTop()
    }

    override fun onZeroItemsLoaded() {
        Timber.d("onZeroItemsLoaded")
        BoundaryState.zeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Game) {

        BoundaryState.itemLoadedAtBottom()
    }

    fun refresh(){
        Timber.d("refresh")
        _boundaryState.value = BoundaryState.zeroItemsLoaded()
    }

}