package com.optimizer.tooltips

import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.optimizer.tooltips.tips.Tip
import java.util.*
import android.util.Xml
import com.optimizer.tooltips.optimizer.R

object TipsManager {

    fun showTips(viewGroup: ViewGroup, tipsQueue: () -> Queue<Tip>) {
        viewGroup.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewGroup.viewTreeObserver.removeOnGlobalLayoutListener(this)
                addTipsLayout(viewGroup, tipsQueue.invoke())
            }
        })
    }

    private fun addTipsLayout(viewGroup: ViewGroup, tipsQueue: Queue<Tip>) {

        val tooltipLayout = TooltipLayout(viewGroup.context)
        tooltipLayout.renderTooltips(tipsQueue)
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