package com.optimizer.tooltips.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator

abstract class BaseViewAnimator {

    var animatorAgent: AnimatorSet
        private set

    private var mDuration = DURATION
    private var mRepeatTimes = 0
    private var mRepeatMode = ValueAnimator.RESTART

    init {
        animatorAgent = AnimatorSet()
    }

    val startDelay: Long
        get() = animatorAgent.startDelay

    val isRunning: Boolean
        get() = animatorAgent.isRunning

    val isStarted: Boolean
        get() = animatorAgent.isStarted

    protected abstract fun prepare(target: View)

    fun setTarget(target: View): BaseViewAnimator {
        reset(target)
        prepare(target)
        return this
    }

    fun animate() {
        start()
    }

    fun restart() {
        animatorAgent = animatorAgent.clone()
        start()
    }

    fun reset(target: View) {
        target.alpha = 1f
        target.scaleX = 1f
        target.scaleY = 1f
        target.rotation = 0f
        target.rotationX = 0f
        target.rotationY = 0f
    }

    fun start() {
        for (animator in animatorAgent.childAnimations) {
            if (animator is ValueAnimator) {
                animator.repeatCount = mRepeatTimes
                animator.repeatMode = mRepeatMode
            }
        }
        animatorAgent.duration = mDuration
        animatorAgent.start()
    }

    fun setDuration(duration: Long): BaseViewAnimator {
        mDuration = duration
        return this
    }

    fun setStartDelay(delay: Long): BaseViewAnimator {
        animatorAgent.startDelay = delay
        return this
    }

    fun addAnimatorListener(l: Animator.AnimatorListener): BaseViewAnimator {
        animatorAgent.addListener(l)
        return this
    }

    fun cancel() {
        animatorAgent.cancel()
    }

    fun removeAnimatorListener(l: Animator.AnimatorListener) {
        animatorAgent.removeListener(l)
    }

    fun removeAllListener() {
        animatorAgent.removeAllListeners()
    }

    fun setInterpolator(interpolator: Interpolator): BaseViewAnimator {
        animatorAgent.interpolator = interpolator
        return this
    }

    fun getDuration(): Long {
        return mDuration
    }

    fun setRepeatTimes(repeatTimes: Int): BaseViewAnimator {
        mRepeatTimes = repeatTimes
        return this
    }

    fun setRepeatMode(repeatMode: Int): BaseViewAnimator {
        mRepeatMode = repeatMode
        return this
    }

    companion object {

        val DURATION: Long = 1000
    }
}
