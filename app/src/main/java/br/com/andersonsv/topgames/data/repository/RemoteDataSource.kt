package br.com.andersonsv.topgames.data.repository

import br.com.andersonsv.topgames.data.network.GameData
import br.com.andersonsv.topgames.domain.vo.LoadingStatus
import java.util.Date

interface RemoteDataSource {
    fun fetchItems(fetchDate : Date,
                   onSuccess: (games : List<GameData>) -> Unit,
                   onStatus: (status : LoadingStatus) -> Unit)
}