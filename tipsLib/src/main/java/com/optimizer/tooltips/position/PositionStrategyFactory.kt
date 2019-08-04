package com.optimizer.tooltips.position

enum class TipHorizontalGravity {
    LEFT, RIGHT, CENTER
}

enum class TipVerticalGravity {
    TOP, BOTTOM
}

internal object PositionStrategyFactory {

    internal fun createPositionStrategy(position: TipVerticalGravity, horizontalGravity: TipHorizontalGravity): PositionCalculator {
        return when (position) {
            TipVerticalGravity.TOP -> TopStrategy(horizontalGravity)
            TipVerticalGravity.BOTTOM -> BottomStrategy(horizontalGravity)
            else -> throw RuntimeException("Unknown position strategy $position")
        }
    }
}
