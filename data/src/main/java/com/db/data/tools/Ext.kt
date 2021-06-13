package com.db.data.tools

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

object Ext {
    fun View.setColorByRes(colorRes: Int) {
        val color = ContextCompat.getColor(this.context, colorRes)
        setBackgroundColor(color)
    }

    fun TextView.setTextColorByRes(colorRes: Int) {
        val color = ContextCompat.getColor(this.context, colorRes)
        setTextColor(color)
    }

    fun View.setColorByValue( colorValue: String) {
        val color = Color.parseColor(colorValue)
        setBackgroundColor(color)
    }
}