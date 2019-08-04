package com.optimizer.tooltips.tips

import android.content.Context
import android.support.annotation.Size
import android.view.View
import android.view.ViewGroup
import com.optimizer.tooltips.CloneView
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.BaseViewAnimator
import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point
import com.optimizer.tooltips.ext.getStatusBarHeight
import com.optimizer.tooltips.position.PositionStrategyFactory
import com.optimizer.tooltips.position.TipHorizontalGravity
import com.optimizer.tooltips.position.TipVerticalGravity
import com.optimizer.tooltips.position.PositionCalculator

class Tooltip(builder: Builder) : Tip {

    private val anchorView: View
    private val tooltipView: View
    private val anchorWindowPosition: Point
    private var positionStrategy: PositionCalculator
    private var enterAnimator: AnimationComposer<BaseViewAnimator>? = null
    private var exitAnimator: AnimationComposer<BaseViewAnimator>? = null

    init {
        tooltipView = builder.tooltipView
        positionStrategy = builder.positionStrategy
        anchorView = builder.anchorView
        anchorWindowPosition = builder.anchorWindowPosition
        enterAnimator = builder.enterAnimation
        exitAnimator = builder.exitAnimation
    }

    override fun getTipView() = tooltipView

    override fun calculatePosition() {
        val position = positionStrategy.computePosition(extractTipViewBounds(), extractAnchorViewBounds(),
                anchorWindowPosition, extractTipViewMargins())

        tooltipView.x = position.x
        tooltipView.y = position.y
    }

    override fun getEnterAnimation() = enterAnimator

    override fun getExitAnimation() = exitAnimator

    override fun createCloneOfAnchorView(context: Context) = CloneView(context).also {
        it.source = anchorView
        it.x = anchorWindowPosition.x
        it.y = anchorWindowPosition.y
    }

    private fun extractTipViewBounds(): Bounds {
        return Bounds(tooltipView.left.toFloat(), tooltipView.top.toFloat(),
                tooltipView.right.toFloat(), tooltipView.bottom.toFloat())
    }

    private fun extractAnchorViewBounds(): Bounds {
        return Bounds(anchorView.left.toFloat(), anchorView.top.toFloat(),
                anchorView.right.toFloat(), anchorView.bottom.toFloat())
    }

    @Size(value = 4)
    private fun extractTipViewMargins(): IntArray {
        val layoutParams = tooltipView.layoutParams as ViewGroup.MarginLayoutParams
        return intArrayOf(layoutParams.leftMargin, layoutParams.topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin)
    }

    class Builder {

        lateinit var positionStrategy: PositionCalculator
        lateinit var anchorView: View
        lateinit var tooltipView: View
        lateinit var anchorWindowPosition: Point
        var enterAnimation: AnimationComposer<BaseViewAnimator>? = null
        var exitAnimation: AnimationComposer<BaseViewAnimator>? = null

        fun withEnterAnimation(animator: AnimationComposer<BaseViewAnimator>): Builder {
            this.enterAnimation = animator
            return this
        }

        fun withExitAnimation(animator: AnimationComposer<BaseViewAnimator>): Builder {
            this.exitAnimation = animator
            return this
        }

        fun attachTooltipView(view: View): Builder {
            this.tooltipView = view
            return this
        }

        fun withGravity(verticalGravity: TipVerticalGravity, horizontalGravity: TipHorizontalGravity): Builder {
            this.positionStrategy = PositionStrategyFactory.createPositionStrategy(verticalGravity, horizontalGravity)
            return this
        }

        fun withAnchorView(anchorView: View): Builder {
            this.anchorView = anchorView
            calculateAnchorCoordinates(anchorView)
            return this
        }

        fun build() = Tooltip(this)

        private fun calculateAnchorCoordinates(anchorView: View): Builder {
            val viewLocation = IntArray(2)
            anchorView.getLocationInWindow(viewLocation)
            anchorWindowPosition = Point(viewLocation[0].toFloat(),
                    (viewLocation[1] - anchorView.context.getStatusBarHeight()).toFloat())
            return this
        }
    }
}
