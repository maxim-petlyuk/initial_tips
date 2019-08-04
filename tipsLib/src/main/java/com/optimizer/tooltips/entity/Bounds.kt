package com.optimizer.tooltips.entity

data class Bounds(val left: Float, val top: Float, val right: Float, val bottom: Float) {

    fun getHeight() = bottom - top

    fun getWidth() = right - left
}