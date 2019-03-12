package br.com.andersonsv.topgames

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import br.com.andersonsv.topgames.data.database.GameDao
import br.com.andersonsv.topgames.data.database.GameDb
import br.com.andersonsv.topgames.data.database.LocalData
import br.com.andersonsv.topgames.data.network.RemoteData
import br.com.andersonsv.topgames.data.network.TwitchService
import br.com.andersonsv.topgames.data.repository.GameListDataRepository
import br.com.andersonsv.topgames.data.repository.LocalDataSource
import br.com.andersonsv.topgames.data.repository.RemoteDataSource
import br.com.andersonsv.topgames.domain.gamelist.GameListRepository
import br.com.andersonsv.topgames.domain.gamelist.GameListUseCase
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    // provide dependency objects
    private fun provideGameListUseCase(): GameListUseCase {
        return GameListUseCase(provideGameListRepository())
    }

    private fun provideGameListRepository(): GameListRepository {
        return GameListDataRepository(provideAppExecutors(), provideLocalData(), provideRemoteData())
    }


    private fun provideDb(): GameDb {
        return GameDb.getInstance(this)
    }

    private fun provideMovieDao(): GameDao {
        return provideDb().gameDao()
    }

    private fun provideTwitchService(): TwitchService {
        return TwitchService.getInstance()
    }

    private fun provideLocalData(): LocalDataSource {
        return LocalData(provideAppExecutors(), provideMovieDao())
    }

    private fun provideRemoteData(): RemoteDataSource {
        return RemoteData(provideTwitchService())
    }

    private fun provideAppExecutors(): AppExecutors {
        return AppExecutors.getInstance()
    }
}