package com.optimizer.tooltips.positionStrategy.strategies

import com.optimizer.tooltips.tips.Tooltip
import java.lang.RuntimeException

enum class TipGravity {
    LEFT, RIGHT, CENTER
}

abstract class BaseStrategy(val gravity: TipGravity) : PositionStrategy {

    fun calculatePosX(tooltip: Tooltip<*>, gravity: TipGravity, leftMargin: Int, rightMargin: Int): Float {
        val anchorViewCenterX = tooltip.anchorX + tooltip.anchorView.measuredWidth / 2

        when (gravity) {
            TipGravity.LEFT -> return (tooltip.anchorView.left + leftMargin).toFloat()

            TipGravity.CENTER -> return anchorViewCenterX - tooltip.tooltipView.width / 2

            TipGravity.RIGHT -> return (tooltip.anchorView.right - tooltip.tooltipView.width - rightMargin).toFloat()

            else -> throw RuntimeException("Unknown gravity: ${gravity.name}")
        }
    }
}
