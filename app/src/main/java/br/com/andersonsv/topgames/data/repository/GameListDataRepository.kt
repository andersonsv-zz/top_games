package br.com.andersonsv.topgames.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import br.com.andersonsv.topgames.AppExecutors
import br.com.andersonsv.topgames.domain.entity.Game
import br.com.andersonsv.topgames.domain.gamelist.GameListRepository
import br.com.andersonsv.topgames.domain.vo.BoundaryState
import br.com.andersonsv.topgames.domain.vo.LoadingStatus
import br.com.andersonsv.topgames.domain.vo.Status
import timber.log.Timber

class GameListDataRepository (
    private val appExecutors: AppExecutors,
    private val localData: LocalDataSource,
    private val remoteData: RemoteDataSource
) : GameListRepository {

    private val loadingStatus = MutableLiveData<LoadingStatus>()

    override
    fun getGames(): LiveData<PagedList<Game>> {
        return localData.getGames()
    }

    override fun getBoundaryState(): LiveData<BoundaryState> {
        return localData.getBoundaryState()
    }

    override fun refresh() {
        localData.refresh()
    }

    override
    fun fetchMore() : LiveData<LoadingStatus> {
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.loading()
            Timber.d("fetchMore starting")
            remoteData.fetchItems({ games ->
                appExecutors.diskIO().execute {
                    localData.insertGames(games)
                    Timber.d("fetchMore saved")
                }
            }, {status ->
                loadingStatus.value = status
            })
        } else{
            Timber.d("fetchMore already loading ")
        }
        return loadingStatus
    }

    override
    fun returnLoadingOrSuccess() : LiveData<LoadingStatus> {
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.success()
        }
        return loadingStatus
    }
}