package com.optimizer.tooltips.position.strategies

import android.graphics.PointF
import android.view.View

import com.optimizer.tooltips.tips.Tooltip

interface PositionStrategy {

    fun calculatePosition(tooltip: Tooltip, tipView: View): PointF
}
