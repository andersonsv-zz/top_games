package br.com.andersonsv.topgames.data.network

import br.com.andersonsv.topgames.data.GameData
import br.com.andersonsv.topgames.data.repository.RemoteDataSource
import br.com.andersonsv.topgames.domain.vo.ErrorCode
import br.com.andersonsv.topgames.domain.vo.LoadingStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException


class RemoteData(private val twitchService: TwitchService) :
    RemoteDataSource {

    override fun fetchItems(
        onSuccess: (movies: List<GameData>) -> Unit,
        onStatus: (status: LoadingStatus) -> Unit
    ) {
        //TODO repassar offset
        val call = twitchService.getGames(10,1)
        call.enqueue(object : Callback<List<GameData>?> {
            override fun onResponse(call: Call<List<GameData>?>?, response: Response<List<GameData>?>?) {
                if (response != null) {
                    if (response.body()?.size == 0) {
                        onStatus(
                            LoadingStatus.error(
                                ErrorCode.NO_DATA))
                    } else {
                        response.body()?.let {
                            onSuccess(it)
                            Timber.d("fetchMore saved")
                        }
                        onStatus(LoadingStatus.success())
                    }
                }
            }

            override fun onFailure(call: Call<List<GameData>?>?, t: Throwable?) {
                if (t is IOException) {
                    onStatus(
                        LoadingStatus.error(
                            ErrorCode.NETWORK_ERROR, t.message))
                } else {
                    onStatus(
                        LoadingStatus.error(
                            ErrorCode.UNKNOWN_ERROR, null))
                }
            }
        })
    }
}