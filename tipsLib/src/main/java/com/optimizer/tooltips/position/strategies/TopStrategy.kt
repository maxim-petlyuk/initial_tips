package com.optimizer.tooltips.position.strategies

import android.graphics.PointF
import android.view.View
import android.view.ViewGroup
import com.optimizer.tooltips.position.TipHorizontalGravity
import com.optimizer.tooltips.tips.Tooltip

class TopStrategy(gravity: TipHorizontalGravity) : BaseStrategy(gravity) {

    override fun calculatePosition(tooltip: Tooltip, tipView: View): PointF {
        val tipHeight = tipView.height

        val layoutParams = tipView.layoutParams as ViewGroup.MarginLayoutParams
        val bottomMargin = layoutParams.bottomMargin

        val xStart = calculatePosX(tooltip, gravity, layoutParams.leftMargin, layoutParams.rightMargin)
        val yStart = tooltip.anchorY - tipHeight.toFloat() - bottomMargin.toFloat()

        return PointF(xStart, yStart)
    }
}
