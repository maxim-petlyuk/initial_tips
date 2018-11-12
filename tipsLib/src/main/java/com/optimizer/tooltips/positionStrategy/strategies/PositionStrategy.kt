package com.optimizer.tooltips.positionStrategy.strategies

import android.view.View

import com.optimizer.tooltips.tips.Tooltip

interface PositionStrategy {

    fun calculatePosition(tooltip: Tooltip<*>, tipView: View)
}
