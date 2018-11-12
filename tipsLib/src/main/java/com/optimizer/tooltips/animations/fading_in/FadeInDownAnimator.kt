package com.optimizer.tooltips.animations.fading_in

import android.animation.ObjectAnimator
import android.view.View

import com.optimizer.tooltips.animations.BaseViewAnimator

class FadeInDownAnimator : BaseViewAnimator() {

    public override fun prepare(target: View) {
        animatorAgent.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0F, 1F),
                ObjectAnimator.ofFloat(target, "translationY", -target.height / 4F, 0F))
    }
}
