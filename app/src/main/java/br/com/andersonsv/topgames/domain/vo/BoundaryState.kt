package br.com.andersonsv.topgames.domain.vo


enum class Direction {
    NONE,
    TOP,
    BOTTOM
}

data class BoundaryState(val direction: Direction) {
    companion object {
        fun  zeroItemsLoaded(): BoundaryState {
            return BoundaryState(
                Direction.NONE
            )
        }

        fun itemLoadedAtTop(): BoundaryState {
            return BoundaryState(
                Direction.TOP
            )
        }

        fun itemLoadedAtBottom(): BoundaryState {
            return BoundaryState(
                Direction.BOTTOM
            )
        }
    }
}