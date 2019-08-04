package com.optimizer.tooltips.position

import com.optimizer.tooltips.position.strategies.BottomStrategy
import com.optimizer.tooltips.position.strategies.PositionStrategy
import com.optimizer.tooltips.position.strategies.TopStrategy

enum class TipHorizontalGravity {
    LEFT, RIGHT, CENTER
}

enum class TipVerticalGravity {
    TOP, BOTTOM
}

internal object PositionStrategyFactory {

    internal fun createPositionStrategy(position: TipVerticalGravity, horizontalGravity: TipHorizontalGravity): PositionStrategy {
        return when (position) {
            TipVerticalGravity.TOP -> TopStrategy(horizontalGravity)
            TipVerticalGravity.BOTTOM -> BottomStrategy(horizontalGravity)
            else -> throw RuntimeException("Unknown position strategy $position")
        }
    }
}
