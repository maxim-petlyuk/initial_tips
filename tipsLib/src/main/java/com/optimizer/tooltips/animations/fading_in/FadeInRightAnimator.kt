package com.optimizer.tooltips.animations.fading_in

import android.animation.ObjectAnimator
import android.view.View

import com.optimizer.tooltips.animations.BaseViewAnimator

class FadeInRightAnimator : BaseViewAnimator() {

    public override fun prepare(target: View) {
        animatorAgent.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0F, 1F),
                ObjectAnimator.ofFloat(target, "translationX", target.width / 4F, 0F)
        )
    }
}
