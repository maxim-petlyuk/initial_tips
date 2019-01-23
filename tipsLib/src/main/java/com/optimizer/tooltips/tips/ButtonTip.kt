package com.optimizer.tooltips.tips

import android.view.View
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.BaseViewAnimator
import com.optimizer.tooltips.positionStrategy.strategies.PositionStrategy

class ButtonTip private constructor(builder: Builder) : Tooltip<View>(builder) {

    class Builder : AbstractBuilder<View>() {

        override fun attachTooltipView(view: View): Builder {
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
