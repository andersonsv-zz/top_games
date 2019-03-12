package br.com.andersonsv.topgames.domain.vo


enum class Direction {
    NONE,
    TOP,
    BOTTOM
}

data class BoundaryState(val direction: Direction) {
    companion object {
        fun <T> zeroItemsLoaded(): BoundaryState {
            return BoundaryState(
                Direction.NONE
            )
        }

        fun <T> itemLoadedAtTop(): BoundaryState {
            return BoundaryState(
                Direction.TOP
            )
        }

        fun <T> itemLoadedAtBottom(): BoundaryState {
            return BoundaryState(
                Direction.BOTTOM
            )
        }
    }
}