package com.optimizer.tooltips.animations

import android.animation.Animator
import android.animation.ValueAnimator
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.Interpolator
import com.optimizer.tooltips.animations.BaseViewAnimator.Companion.DURATION
import java.util.ArrayList

interface AnimatorCallback {
    fun call(animator: Animator)
}

private val NO_DELAY: Long = 0
private val INFINITE = -1
private val CENTER_PIVOT = Float.MAX_VALUE

class AnimationComposer<out Animation : BaseViewAnimator>(animation: Animation) {

    private val callbacks = ArrayList<Animator.AnimatorListener>()
    private var animator: Animation = animation
    private var duration = DURATION
    private var delay = NO_DELAY
    private var repeat = false
    private var repeatTimes = 0
    private var repeatMode = ValueAnimator.RESTART
    private var pivotX = CENTER_PIVOT
    private var pivotY = CENTER_PIVOT
    private var interpolator: Interpolator? = null
    private lateinit var target: View

    fun duration(duration: Long): AnimationComposer<Animation> {
        this.duration = duration
        return this
    }

    fun delay(delay: Long): AnimationComposer<Animation> {
        this.delay = delay
        return this
    }

    fun interpolate(interpolator: Interpolator): AnimationComposer<Animation> {
        this.interpolator = interpolator
        return this
    }

    fun pivot(pivotX: Float, pivotY: Float): AnimationComposer<Animation> {
        this.pivotX = pivotX
        this.pivotY = pivotY
        return this
    }

    fun pivotX(pivotX: Float): AnimationComposer<Animation> {
        this.pivotX = pivotX
        return this
    }

    fun pivotY(pivotY: Float): AnimationComposer<Animation> {
        this.pivotY = pivotY
        return this
    }

    fun repeat(times: Int): AnimationComposer<Animation> {
        if (times < INFINITE) {
            throw RuntimeException("Can not be less than -1, -1 is infinite loop")
        }

        repeat = times != 0
        repeatTimes = times
        return this
    }

    fun repeatMode(mode: Int): AnimationComposer<Animation> {
        repeatMode = mode
        return this
    }

    fun withListener(listener: Animator.AnimatorListener): AnimationComposer<Animation> {
        callbacks.add(listener)
        return this
    }

    fun onStart(callback: AnimatorCallback): AnimationComposer<Animation> {
        callbacks.add(object : EmptyAnimatorListener() {
            override fun onAnimationStart(animation: Animator) {
                callback.call(animation)
            }
        })
        return this
    }

    fun onEnd(callback: AnimatorCallback): AnimationComposer<Animation> {
        callbacks.add(object : EmptyAnimatorListener() {
            override fun onAnimationEnd(animation: Animator) {
                callback.call(animation)
            }
        })
        return this
    }

    fun onCancel(callback: AnimatorCallback): AnimationComposer<Animation> {
        callbacks.add(object : EmptyAnimatorListener() {
            override fun onAnimationCancel(animation: Animator) {
                callback.call(animation)
            }
        })
        return this
    }

    fun onRepeat(callback: AnimatorCallback): AnimationComposer<Animation> {
        callbacks.add(object : EmptyAnimatorListener() {
            override fun onAnimationRepeat(animation: Animator) {
                callback.call(animation)
            }
        })
        return this
    }

    fun playOn(target: View) {
        this.target = target
        play()
    }

    private fun play(): BaseViewAnimator {
        animator.setTarget(target)

        target.pivotX = if (pivotX == CENTER_PIVOT) target.getMeasuredWidth() / 2.0f else pivotX
        target.pivotY = if (pivotY == CENTER_PIVOT) target.getMeasuredHeight() / 2.0f else pivotY

        animator.setDuration(duration)
                .setRepeatTimes(repeatTimes)
                .setRepeatMode(repeatMode)
                .setStartDelay(delay)

        interpolator?.let { animator.setInterpolator(it) }

        if (callbacks.size > 0) {
            for (callback in callbacks) {
                animator.addAnimatorListener(callback)
            }
        }
        animator.animate()
        return animator
    }
}
