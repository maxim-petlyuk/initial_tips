package com.optimizer.tooltips

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class CloneView : View {

    var source: View? = null
        set(source) {
            field = source
            invalidate()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        if (source != null) {
            source!!.draw(canvas)
        }
    }
}