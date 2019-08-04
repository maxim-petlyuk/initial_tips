package com.optimizer.tooltips.position

import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point
import com.optimizer.tooltips.utils.MARGIN_BOTTOM_INDEX

class TopStrategy(gravity: TipHorizontalGravity) : AbstractPositionStrategy(gravity) {

    override fun computePosition(tipViewBounds: Bounds, anchorViewBounds: Bounds,
                                 anchorWindowPosition: Point, margins: IntArray): Point {
        val xStart = calculatePosX(tipViewBounds, anchorViewBounds, anchorWindowPosition, margins)
        val yStart = anchorWindowPosition.y - tipViewBounds.getHeight() - margins[MARGIN_BOTTOM_INDEX].toFloat()

        return Point(xStart, yStart)
    }
}
