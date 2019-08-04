package com.optimizer.tooltips.position

import android.support.annotation.Size
import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point

interface PositionCalculator {

    /**
     * Method will calculate X, Y coordinates of the tip view inside the screen boundaries
     * @param anchorWindowPosition - x, y position of the anchored view
     * @param anchorWindowPosition - x, y position of the anchored view
     * @param tipViewBounds - left, top, right, bottom bounds of the tip view
     * @param anchorViewBounds - left, top, right, bottom bounds of the anchored  view
     * @param margins - extra space for each tip view side. Array should consist of 4 values:
     * left, top, right, bottom
     */
    fun computePosition(tipViewBounds: Bounds, anchorViewBounds: Bounds, anchorWindowPosition: Point,
                        @Size(value = 4) margins: IntArray): Point
}
