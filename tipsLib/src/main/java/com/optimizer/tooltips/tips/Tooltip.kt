package com.optimizer.tooltips.tips

import android.content.Context
import android.view.View
import com.optimizer.tooltips.CloneView
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.BaseViewAnimator
import com.optimizer.tooltips.positionStrategy.strategies.PositionStrategy

abstract class Tooltip<V : View>(builder: AbstractBuilder<V>) : Tip {

    var x: Float = 0.toFloat()
        private set

    var y: Float = 0.toFloat()
        private set

    var anchorX: Float = 0.toFloat()
        private set

    var anchorY: Float = 0.toFloat()
        private set

    var anchorView: View
        private set

    var positionStrategy: PositionStrategy

    var tooltipView: V

    var enterAnimator: AnimationComposer<BaseViewAnimator>? = null

    var exitAnimator: AnimationComposer<BaseViewAnimator>? = null

    init {
        tooltipView = builder.tooltipView
        positionStrategy = builder.positionStrategy
        anchorView = builder.anchorView
        anchorX = builder.anchorX
        anchorY = builder.anchorY
        enterAnimator = builder.enterAnimation
        exitAnimator = builder.exitAnimation
    }

    fun attachAnchorView(anchorView: View) {
        this.anchorView = anchorView
    }

    fun updateLocation(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    override fun decorateView() {}

    override fun invalidate() {
        tooltipView.x = x
        tooltipView.y = y
    }

    override fun getTipView(): View {
        return tooltipView
    }

    override fun calculatePosition() {
        positionStrategy.calculatePosition(this, tooltipView)
    }

    override fun getEnterAnimation(): AnimationComposer<BaseViewAnimator>? = enterAnimator

    override fun getExitAnimation(): AnimationComposer<BaseViewAnimator>? = exitAnimator

    override fun createCloneOfTipView(context: Context): View {
        val cloneView = CloneView(context)
        cloneView.source = anchorView
        cloneView.x = anchorX
        cloneView.y = anchorY
        return cloneView
    }

    abstract class AbstractBuilder<V : View> {

        lateinit var positionStrategy: PositionStrategy
        lateinit var anchorView: View
        lateinit var tooltipView: V
        var anchorX: Float = 0F
        var anchorY: Float = 0F
        var enterAnimation: AnimationComposer<BaseViewAnimator>? = null
        var exitAnimation: AnimationComposer<BaseViewAnimator>? = null

        open fun withEnterAnimation(animator: AnimationComposer<BaseViewAnimator>): AbstractBuilder<V> {
            this.enterAnimation = animator
            return this
        }

        open fun withExitAnimation(animator: AnimationComposer<BaseViewAnimator>): AbstractBuilder<V> {
            this.exitAnimation = animator
            return this
        }

        open fun attachTooltipView(view: V): AbstractBuilder<V> {
            this.tooltipView = view
            return this
        }

        open fun withPositionStrategy(strategy: PositionStrategy): AbstractBuilder<V> {
            this.positionStrategy = strategy
            return this
        }

        open fun withAnchorView(anchorView: View): AbstractBuilder<V> {
            this.anchorView = anchorView
            calculateAnchorCoordinates(anchorView)
            return this
        }

        private fun calculateAnchorCoordinates(anchorView: View): AbstractBuilder<V> {
            val viewLocation = IntArray(2)
            anchorView.getLocationInWindow(viewLocation)

            anchorX = viewLocation[0].toFloat()
            anchorY = (viewLocation[1] - getStatusBarHeight(anchorView.context)).toFloat()
            return this
        }

        private fun getStatusBarHeight(context: Context): Int {
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
        }
    }
}
