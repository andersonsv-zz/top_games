package br.com.andersonsv.topgames.data.repository

import br.com.andersonsv.topgames.data.GameData
import br.com.andersonsv.topgames.domain.vo.LoadingStatus

interface RemoteDataSource {
    fun fetchItems(onSuccess: (games : List<GameData>) -> Unit,
                   onStatus: (status : LoadingStatus) -> Unit)
}