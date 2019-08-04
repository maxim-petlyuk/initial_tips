package com.optimizer.tooltips

import android.animation.Animator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.optimizer.tooltips.animations.AnimatorCallback
import com.optimizer.tooltips.tips.Tip
import java.lang.ref.WeakReference
import java.util.*

class TooltipLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var tipsQueue: Queue<Tip>? = null
    private var currentTip: Tip? = null

    init {
        initialize()
    }

    private fun initialize() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.black_30))
    }

    fun renderTooltips(tooltipsQueue: Queue<Tip>?): Unit {
        tipsQueue = tooltipsQueue
        val tip = tipsQueue?.poll()

        if (tip == null) {
            removeTooltipWindow()
            return
        }

        val tooltipView = tip.getTipView()
        tooltipView.addOnLayoutChangeListener(TooltipViewLayoutChangeListener(tip))

        setOnClickListener {
            removePreviousTooltip { renderTooltips(tipsQueue) }
        }

        tooltipView.layoutParams ?: setDefaultLayoutParams(tooltipView)

        currentTip = tip

        tip.getEnterAnimation()?.let { enterAnim ->
            tooltipView.alpha = 0f
            addView(tooltipView)
            enterAnim.playOn(tooltipView)
        } ?: run {
            addView(tooltipView)
        }
    }

    private fun setDefaultLayoutParams(tooltipView: View) {
        tooltipView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    private fun removePreviousTooltip(onNext: () -> Unit) {
        currentTip?.let { tip ->
            tip.getExitAnimation()?.let { exitAnim ->
                exitAnim.onEnd(object : AnimatorCallback {
                    override fun call(animator: Animator) {
                        removeAllViews()
                        onNext.invoke()
                    }
                }).playOn(tip.getTipView())
            } ?: run {
                removeAllViews()
                onNext.invoke()
            }
        }
    }

    private fun removeTooltipWindow() {
        val activityView = parent
        if (activityView is ViewGroup) {
            activityView.removeView(this)
        }
    }

    private inner class TooltipViewLayoutChangeListener constructor(tip: Tip) : OnLayoutChangeListener {

        private val tipRef: WeakReference<Tip> = WeakReference(tip)

        override fun onLayoutChange(view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            view.removeOnLayoutChangeListener(this)

            tipRef.get()?.let { tip ->
                tip.calculatePosition()

                post {
                    addView(tip.createCloneOfAnchorView(context))
                }
            }
        }
    }
}
