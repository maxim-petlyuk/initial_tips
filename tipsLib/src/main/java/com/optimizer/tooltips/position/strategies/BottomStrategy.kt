package com.optimizer.tooltips.position.strategies

import android.graphics.PointF
import android.view.View
import android.view.ViewGroup
import com.optimizer.tooltips.position.TipHorizontalGravity
import com.optimizer.tooltips.tips.Tooltip

class BottomStrategy(gravity: TipHorizontalGravity) : BaseStrategy(gravity) {

    override fun calculatePosition(tooltip: Tooltip, tipView: View): PointF {
        val anchorView = tooltip.anchorView

        val layoutParams = tipView.layoutParams as ViewGroup.MarginLayoutParams
        val topMargin = layoutParams.topMargin

        val xStart: Float = calculatePosX(tooltip, gravity, layoutParams.leftMargin, layoutParams.rightMargin)
        val yStart: Float = tooltip.anchorY + anchorView.height.toFloat() + topMargin.toFloat()

        return PointF(xStart, yStart)
    }
}
