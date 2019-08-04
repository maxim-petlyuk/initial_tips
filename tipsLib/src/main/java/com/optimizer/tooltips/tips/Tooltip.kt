package com.optimizer.tooltips.tips

import android.content.Context
import android.view.View
import com.optimizer.tooltips.CloneView
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.BaseViewAnimator
import com.optimizer.tooltips.ext.getStatusBarHeight
import com.optimizer.tooltips.position.PositionStrategyFactory
import com.optimizer.tooltips.position.TipHorizontalGravity
import com.optimizer.tooltips.position.TipVerticalGravity
import com.optimizer.tooltips.position.strategies.PositionStrategy

class Tooltip(builder: Builder) : Tip {

    val anchorX: Float
    val anchorY: Float
    val anchorView: View
    val tooltipView: View
    private var positionStrategy: PositionStrategy
    private var enterAnimator: AnimationComposer<BaseViewAnimator>? = null
    private var exitAnimator: AnimationComposer<BaseViewAnimator>? = null

    init {
        tooltipView = builder.tooltipView
        positionStrategy = builder.positionStrategy
        anchorView = builder.anchorView
        anchorX = builder.anchorX
        anchorY = builder.anchorY
        enterAnimator = builder.enterAnimation
        exitAnimator = builder.exitAnimation
    }

    override fun getTipView() = tooltipView

    override fun calculatePosition() {
        val position = positionStrategy.calculatePosition(this, tooltipView)
        tooltipView.x = position.x
        tooltipView.y = position.y
    }

    override fun getEnterAnimation() = enterAnimator

    override fun getExitAnimation() = exitAnimator

    override fun createCloneOfAnchorView(context: Context) = CloneView(context).also {
        it.source = anchorView
        it.x = anchorX
        it.y = anchorY
    }

    class Builder {

        lateinit var positionStrategy: PositionStrategy
        lateinit var anchorView: View
        lateinit var tooltipView: View
        var anchorX: Float = 0F
        var anchorY: Float = 0F
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

        fun build(): Tooltip {
            return Tooltip(this)
        }

        private fun calculateAnchorCoordinates(anchorView: View): Builder {
            val viewLocation = IntArray(2)
            anchorView.getLocationInWindow(viewLocation)

            anchorX = viewLocation[0].toFloat()
            anchorY = (viewLocation[1] - anchorView.context.getStatusBarHeight()).toFloat()
            return this
        }
    }
}
