package com.optimizer.tooltips.position

import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point
import com.optimizer.tooltips.utils.MARGIN_LEFT_INDEX
import com.optimizer.tooltips.utils.MARGIN_RIGHT_INDEX

abstract class AbstractPositionStrategy(private val gravity: TipHorizontalGravity) : PositionCalculator {

    fun calculatePosX(tipViewBounds: Bounds, anchorViewBounds: Bounds, anchorViewWindowPosition: Point, margins: IntArray): Float {
        val anchorViewCenterX = anchorViewWindowPosition.x + anchorViewBounds.getWidth() / 2

        return when (gravity) {
            TipHorizontalGravity.LEFT -> (anchorViewWindowPosition.x + margins[MARGIN_LEFT_INDEX])

            TipHorizontalGravity.CENTER -> anchorViewCenterX - tipViewBounds.getWidth() / 2

            TipHorizontalGravity.RIGHT -> (anchorViewWindowPosition.x + anchorViewBounds.getWidth() - tipViewBounds.getWidth() - margins[MARGIN_RIGHT_INDEX])

            else -> throw RuntimeException("Unknown gravity: ${gravity.name}")
        }
    }
}
