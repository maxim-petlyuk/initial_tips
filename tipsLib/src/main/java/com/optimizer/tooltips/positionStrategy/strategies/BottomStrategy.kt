package com.optimizer.tooltips.positionStrategy.strategies

import android.view.View
import android.view.ViewGroup
import com.optimizer.tooltips.tips.Tooltip

class BottomStrategy(gravity: TipGravity) : BaseStrategy(gravity) {

    override fun calculatePosition(tooltip: Tooltip<*>, tipView: View) {
        val anchorView = tooltip.anchorView

        val layoutParams = tipView.layoutParams as ViewGroup.MarginLayoutParams
        val topMargin = layoutParams.topMargin

        val xStart: Float = calculatePosX(tooltip, gravity, layoutParams.leftMargin, layoutParams.rightMargin)
        val yStart: Float = tooltip.anchorY + anchorView.height.toFloat() + topMargin.toFloat()

        tooltip.updateLocation(xStart, yStart)
    }
}
