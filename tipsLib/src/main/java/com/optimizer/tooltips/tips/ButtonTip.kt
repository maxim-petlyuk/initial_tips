package com.optimizer.tooltips.tips

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.BaseViewAnimator

import com.optimizer.tooltips.positionStrategy.strategies.PositionStrategy

class ButtonTip private constructor(builder: Builder) : Tooltip<TextView>(builder) {

    private val mBackground: Drawable?
    private val mText: String?

    init {
        mBackground = builder.background
        mText = builder.text
    }

    override fun decorateView() {
        tooltipView.setBackground(mBackground)
        tooltipView.text = mText
    }

    class Builder : AbstractBuilder<TextView>() {

        lateinit var text: String
        var background: Drawable? = null

        fun withBackround(drawable: Drawable): Builder {
            this.background = drawable
            return this
        }

        fun withText(text: String): Builder {
            this.text = text
            return this
        }

        override fun attachTooltipView(view: TextView): Builder {
            return super.attachTooltipView(view) as Builder
        }

        override fun withPositionStrategy(strategy: PositionStrategy): Builder {
            return super.withPositionStrategy(strategy) as Builder
        }

        override fun withAnchorView(anchorView: View): Builder {
            return super.withAnchorView(anchorView) as Builder
        }

        override fun withEnterAnimation(animator: AnimationComposer<BaseViewAnimator>): Builder {
            return super.withEnterAnimation(animator) as Builder
        }

        override fun withExitAnimation(animator: AnimationComposer<BaseViewAnimator>): Builder {
            return super.withExitAnimation(animator) as Builder
        }

        fun buildTooltip(): Tip {
            return ButtonTip(this)
        }
    }
}
