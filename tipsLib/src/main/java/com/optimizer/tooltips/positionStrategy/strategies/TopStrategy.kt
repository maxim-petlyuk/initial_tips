package com.optimizer.tooltips.positionStrategy.strategies

import android.view.View
import android.view.ViewGroup
import com.optimizer.tooltips.tips.Tooltip

class TopStrategy(gravity: TipGravity) : BaseStrategy(gravity) {

    override fun calculatePosition(tooltip: Tooltip<*>, tipView: View) {
        val tipHeight = tipView.height

        val layoutParams = tipView.layoutParams as ViewGroup.MarginLayoutParams
        val bottomMargin = layoutParams.bottomMargin

        val xStart = calculatePosX(tooltip, gravity, layoutParams.leftMargin, layoutParams.rightMargin)
        val yStart = tooltip.anchorY - tipHeight.toFloat() - bottomMargin.toFloat()

        tooltip.updateLocation(xStart, yStart)
    }
}
