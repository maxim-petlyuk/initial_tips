package com.optimizer.tooltips.position.strategies

import com.optimizer.tooltips.position.TipHorizontalGravity
import com.optimizer.tooltips.tips.Tooltip

abstract class BaseStrategy(protected val gravity: TipHorizontalGravity) : PositionStrategy {

    fun calculatePosX(tooltip: Tooltip, gravity: TipHorizontalGravity, leftMargin: Int, rightMargin: Int): Float {
        val anchorViewCenterX = tooltip.anchorX + tooltip.anchorView.measuredWidth / 2

        return when (gravity) {
            TipHorizontalGravity.LEFT -> (tooltip.anchorView.left + leftMargin).toFloat()

            TipHorizontalGravity.CENTER -> anchorViewCenterX - tooltip.tooltipView.width / 2

            TipHorizontalGravity.RIGHT -> (tooltip.anchorView.right - tooltip.tooltipView.width - rightMargin).toFloat()

            else -> throw RuntimeException("Unknown gravity: ${gravity.name}")
        }
    }
}
