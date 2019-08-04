package com.optimizer.tooltips.position

import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point
import com.optimizer.tooltips.utils.MARGIN_TOP_INDEX

class BottomStrategy(gravity: TipHorizontalGravity) : AbstractPositionStrategy(gravity) {

    override fun computePosition(tipViewBounds: Bounds, anchorViewBounds: Bounds,
                                 anchorWindowPosition: Point, margins: IntArray): Point {
        val xStart: Float = calculatePosX(tipViewBounds, anchorViewBounds, anchorWindowPosition, margins)
        val yStart: Float = anchorWindowPosition.y + anchorViewBounds.getHeight() + margins[MARGIN_TOP_INDEX].toFloat()

        return Point(xStart, yStart)
    }
}
