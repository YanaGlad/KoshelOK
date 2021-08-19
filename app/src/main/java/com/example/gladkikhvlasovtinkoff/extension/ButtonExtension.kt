package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import android.graphics.Color
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import com.example.gladkikhvlasovtinkoff.R

fun Button.setDisabled(context : Context?) {
    this.background =
        ResourcesCompat.getDrawable(
            resources,
            R.drawable.disabled_button_back_with_corner_radius, context?.theme
        )
    this.isEnabled = false
    this.setTextColor(Color.BLACK)
}

fun Button.setEnabled(context : Context?) {
    this.background =
        ResourcesCompat.getDrawable(
            resources,
            R.drawable.active_button_back_with_corner_radius, context?.theme
        )
    this.isEnabled = true
    this.setTextColor(Color.WHITE)
}

