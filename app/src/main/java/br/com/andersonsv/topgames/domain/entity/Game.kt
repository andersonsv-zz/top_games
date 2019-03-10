package br.com.andersonsv.topgames.domain.entity

data class Game (
    val id: Long = 0,
    val viewers: Float = 0.toFloat(),
    val channels: Float = 0.toFloat()
)
