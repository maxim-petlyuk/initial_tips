package com.optimizer.tooltips

import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.Xml
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.optimizer.tooltips.tips.Tip
import java.util.*

object TipsManager {

    @JvmStatic
    fun showTips(viewGroup: ViewGroup, @ColorInt dimBackgroundColor: Int, tipsQueue: () -> Queue<Tip>) {
        viewGroup.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewGroup.viewTreeObserver.removeOnGlobalLayoutListener(this)
                addTipsLayout(viewGroup, dimBackgroundColor, tipsQueue.invoke())
            }
        })
    }

    private fun addTipsLayout(viewGroup: ViewGroup, @ColorInt dimBackgroundColor: Int, tipsQueue: Queue<Tip>) {
        val tooltipLayout = TooltipLayout(viewGroup.context)
        tooltipLayout.renderTooltips(tipsQueue)
        tooltipLayout.setBackgroundColor(dimBackgroundColor)
        tooltipLayout.layoutParams = viewGroup.generateLayoutParams(fakeAttributeSet(viewGroup))
        viewGroup.addView(tooltipLayout)
    }

    private fun fakeAttributeSet(viewGroup: ViewGroup): AttributeSet {
        val parser = viewGroup.getResources().getXml(R.xml.fake_layout)

        try {
            parser.next()
            parser.nextTag()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Xml.asAttributeSet(parser)
    }
}