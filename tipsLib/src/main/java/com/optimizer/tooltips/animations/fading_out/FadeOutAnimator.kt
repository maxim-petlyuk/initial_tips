package com.optimizer.tooltips.animations.fading_out

import android.animation.ObjectAnimator
import android.view.View

import com.optimizer.tooltips.animations.BaseViewAnimator

class FadeOutAnimator : BaseViewAnimator() {

    public override fun prepare(target: View) {
        animatorAgent.playTogether(ObjectAnimator.ofFloat(target, "alpha", 1F, 0F))
    }
}
