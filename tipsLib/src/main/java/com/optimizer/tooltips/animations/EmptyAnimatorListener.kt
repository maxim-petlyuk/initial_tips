package com.optimizer.tooltips.animations

import android.animation.Animator

internal open class EmptyAnimatorListener : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator) {}

    override fun onAnimationEnd(animation: Animator) {}

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationRepeat(animation: Animator) {}
}