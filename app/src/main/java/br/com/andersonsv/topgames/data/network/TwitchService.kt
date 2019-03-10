package br.com.andersonsv.topgames.data.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchService {

    @GET("games/top")
    @Headers()
    fun getGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

        ): Call<List<GameData>>

    companion object {
        private const val BASE_URL = "https://api.twitch.tv/kraken/"

        private val retrofitService: TwitchService by lazy{
            val interceptor = Interceptor { chain ->
                val request = chain.request()

                val url = request.url().newBuilder()
                    .build()
                val newRequest = request.newBuilder()
                    .addHeader("Accept","application/vnd.twitchtv.v5+json")
                    .addHeader("Client-ID", "hldr7z88tohp6ehnr78hlhnky5pwxl")
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }

            val httpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

            val gson = GsonBuilder()
                .registerTypeAdapterFactory(ItemTypeAdapterFactory())
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            retrofit.create(TwitchService::class.java)
        }

        fun getInstance(): TwitchService {
            return retrofitService
        }
    }
}